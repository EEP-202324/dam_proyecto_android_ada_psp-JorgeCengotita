package example.estudiantes;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class EstudiantesApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    
    
    @Test
    void shouldReturnAEstudiantesWhenDataIsSaved() {
    	ResponseEntity<String> response = restTemplate
                .withBasicAuth("usuario1", "abc123") // Add this
                .getForEntity("/estudiantes/3", String.class);
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
      ResponseEntity<String> response = restTemplate.withBasicAuth("usuario1", "abc123").getForEntity("/estudiantes/1000", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
    @Test
    @DirtiesContext
    void shouldCreateANewEstudiantes() {
       Estudiantes newEstudiantes = new Estudiantes(0, "Pedro", null, null, null, "usuario1");
       ResponseEntity<Void> createResponse = restTemplate.withBasicAuth("usuario1", "abc123").postForEntity("/estudiantes", newEstudiantes, Void.class);
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

       URI locationOfNewEstudiantes = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.withBasicAuth("usuario1", "abc123").getForEntity(locationOfNewEstudiantes, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
       
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

       DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
       Number id = documentContext.read("$.id");
       String nombre = documentContext.read("$.nombre");

       assertThat(id).isNotNull();
       assertThat(nombre).isEqualTo("Pedro");
    }
    
    @Test
    void shouldReturnAllEstudiantesWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("usuario1", "abc123").getForEntity("/estudiantes", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int estudiantesCount = documentContext.read("$.length()");
        assertThat(estudiantesCount).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(3, 4, 5);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactlyInAnyOrder("Pedro", "Juan", "Alberto");

        JSONArray apellidos = documentContext.read("$..apellidos");
        assertThat(apellidos).containsExactlyInAnyOrder("Lopez", "Garcia", "Gomez");

        JSONArray correos = documentContext.read("$..correo");
        assertThat(correos).containsExactlyInAnyOrder("pedrolopez@gmail.com", "juangarcia@gmail.com", "albertogomez@gmail.com");

        JSONArray dnies = documentContext.read("$..dni");
        assertThat(dnies).containsExactlyInAnyOrder("01234567C", "01234567D", "01234567E");
    }

    @Test
    void shouldReturnAPageOfEstudiantes() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("usuario1", "abc123").getForEntity("/estudiantes?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }
    
    @Test
    void shouldReturnASortedPageOfEstudiantes() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("usuario1", "abc123").getForEntity("/estudiantes?page=0&size=1&sort=nombre,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        String nombre = documentContext.read("$[0].nombre");
        assertThat(nombre).isEqualTo("Pedro");
    }
    
    @Test
    void shouldReturnASortedPageOfEstudiantesWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("usuario1", "abc123").getForEntity("/estudiantes", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(3);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactly("Alberto" , "Juan" , "Pedro");
    }
    
    @Test
    void shouldNotReturnEstudiantesWhenUsingBadCredentials() {
        ResponseEntity<String> response = restTemplate
          .withBasicAuth("BAD-USER", "abc123")
          .getForEntity("/estudiantes/3", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        response = restTemplate
          .withBasicAuth("usuario1", "BAD-PASSWORD")
          .getForEntity("/estudiantes/3", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
    
    @Test
    void shouldRejectUsersWhoAreNotEstudiantesOwners() {
        ResponseEntity<String> response = restTemplate
          .withBasicAuth("hank-owns-no-cards", "qrs456")
          .getForEntity("/estudiantes/3", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
    
    @Test
    void shouldNotAllowAccessToEstudiantesTheyDoNotOwn() {
        ResponseEntity<String> response = restTemplate
          .withBasicAuth("usuario1", "abc123")
          .getForEntity("/estudiantes/6", String.class); // Elenas data
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}