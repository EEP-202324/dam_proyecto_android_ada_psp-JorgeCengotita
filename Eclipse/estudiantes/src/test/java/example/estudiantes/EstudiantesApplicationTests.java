package example.estudiantes;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = 
SpringBootTest.WebEnvironment.RANDOM_PORT)
class EstudiantesApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAEstudiantesWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/estudiantes/3", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(3);

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Pedro");
        
        String apellidos = documentContext.read("$.apellidos");
        assertThat(apellidos).isEqualTo("Lopez");
        
        String correo = documentContext.read("$.correo");
        assertThat(correo).isEqualTo("pedrolopez@gmail.com");
        
        String dni = documentContext.read("$.dni");
        assertThat(dni).isEqualTo("01234567C");
    }
    
    @Test
    void shouldNotReturnAEstudiantesWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/estudiantes/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
}