package example.estudiantes;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class EstudiantesJsonTest {

    @Autowired
    private JacksonTester<Estudiantes> json;

    @Test
    void estudiantesSerializationTest() throws IOException {
        Estudiantes estudiantes = new Estudiantes(1, "juan", "palomo", "juanpalomo@gmail.com", "01234567A");
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
    
    @Test
    void estudiantesDeserializationTest() throws IOException {
       String expected = """
               {
                   "id": 2,
                   "nombre":"Pepe",
                   "apellidos":"Perez",
                   "correo":"pepeperez@gmail.com",
                   "dni":"01234567B"
               }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new Estudiantes(2, "Pepe", "Perez", "pepeperez@gmail.com", "01234567B"));
       assertThat(json.parseObject(expected).id()).isEqualTo(2);
       assertThat(json.parseObject(expected).nombre()).isEqualTo("Pepe");
       assertThat(json.parseObject(expected).apellidos()).isEqualTo("Perez");
       assertThat(json.parseObject(expected).correo()).isEqualTo("pepeperez@gmail.com");
       assertThat(json.parseObject(expected).dni()).isEqualTo("01234567B");
    }
}

