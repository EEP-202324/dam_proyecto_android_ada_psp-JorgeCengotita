package example.estudiantes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiantes")
class EstudiantesController {

	@GetMapping("/{requestedId}")
	private ResponseEntity<Estudiantes> findById(@PathVariable Long requestedId) {
	    if (requestedId.equals(3L)) {
	        Estudiantes estudiantes = new Estudiantes(3, "Pedro", "Lopez", "pedrolopez@gmail.com", "01234567C");
	        return ResponseEntity.ok(estudiantes);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}