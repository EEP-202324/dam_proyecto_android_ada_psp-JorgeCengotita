package example.estudiantes;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.assertj.core.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class EstudiantesJsonTest {

    @Autowired
    private JacksonTester<Estudiantes> json;
    
    @Autowired
    private JacksonTester<Estudiantes[]> jsonList;
    
    
    private Estudiantes[] estudiantes;
    @BeforeEach
    void setUp() {
        estudiantes = Arrays.array(
                new Estudiantes(3, "Pedro", "Lopez", "pedrolopez@gmail.com", "01234567C", "usuario1"),
                new Estudiantes(4, "Juan", "Garcia", "juangarcia@gmail.com", "01234567D", "usuario1"),
                new Estudiantes(5, "Alberto", "Gomez", "albertogomez@gmail.com", "01234567E", "usuario1"));
    }
    
    @Test
    void estudiantesListSerializationTest() throws IOException {
       assertThat(jsonList.write(estudiantes)).isStrictlyEqualToJson("list.json");
    }
    
    @Test
    void estudiantesListDeserializationTest() throws IOException {
    	String expected = """
    		    [
    		        { "id": 3, "nombre": "Pedro", "apellidos": "Lopez", "correo": "pedrolopez@gmail.com", "dni": "01234567C", "owner": "usuario1"},
    		        { "id": 4, "nombre": "Juan", "apellidos": "Garcia", "correo": "juangarcia@gmail.com", "dni": "01234567D", "owner": "usuario1"},
    		        { "id": 5, "nombre": "Alberto", "apellidos": "Gomez", "correo": "albertogomez@gmail.com", "dni": "01234567E", "owner": "usuario1" }
    		    ]
    		    """;
       assertThat(jsonList.parse(expected)).isEqualTo(estudiantes);
    }

    @Test
    void estudiantesSerializationTest() throws IOException {
        Estudiantes estudiantes = new Estudiantes(1, "juan", "palomo", "juanpalomo@gmail.com", "01234567A", "usuario1");
        assertThat(json.write(estudiantes)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(estudiantes)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(estudiantes)).extractingJsonPathNumberValue("@.id")
            .isEqualTo(1);

        assertThat(json.write(estudiantes)).hasJsonPathStringValue("@.nombre");
        assertThat(json.write(estudiantes)).extractingJsonPathStringValue("@.nombre")
            .isEqualTo("juan");

        assertThat(json.write(estudiantes)).hasJsonPathStringValue("@.apellidos");
        assertThat(json.write(estudiantes)).extractingJsonPathStringValue("@.apellidos")
            .isEqualTo("palomo");

        assertThat(json.write(estudiantes)).hasJsonPathStringValue("@.correo");
        assertThat(json.write(estudiantes)).extractingJsonPathStringValue("@.correo")
        	.isEqualTo("juanpalomo@gmail.com");
        
        assertThat(json.write(estudiantes)).hasJsonPathStringValue("@.dni"); 
        assertThat(json.write(estudiantes)).extractingJsonPathStringValue("@.dni")
        .isEqualTo("01234567A");
    }
    
//    @Test
//    void estudiantesDeserializationTest() throws IOException {
//       String expected = """
//               {
//                   "id": 2,
//                   "nombre":"Pepe",
//                   "apellidos":"Perez",
//                   "correo":"pepeperez@gmail.com",
//                   "dni":"01234567B",
//                   "owner":"usuario1"
//               }
//               """;
//       assertThat(json.parse(expected))
//               .isEqualTo(new Estudiantes(2, "Pepe", "Perez", "pepeperez@gmail.com", "01234567B", "usuario1"));
//       assertThat(json.parseObject(expected).id()).isEqualTo(2);
//       assertThat(json.parseObject(expected).nombre()).isEqualTo("Pepe");
//       assertThat(json.parseObject(expected).apellidos()).isEqualTo("Perez");
//       assertThat(json.parseObject(expected).correo()).isEqualTo("pepeperez@gmail.com");
//       assertThat(json.parseObject(expected).dni()).isEqualTo("01234567B");
//    }
}

