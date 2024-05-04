package example.estudiantes;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiantes")
class EstudiantesController {
	private final EstudiantesRepository estudiantesRepository;
	
	private EstudiantesController(EstudiantesRepository estudiantesRepository) {
	      this.estudiantesRepository = estudiantesRepository;
	   }

	@GetMapping("/{requestedId}")
	private ResponseEntity<Estudiantes> findById(@PathVariable Integer requestedId) {
		Optional<Estudiantes> estudiantesOptional = estudiantesRepository.findById(requestedId);
		if (estudiantesOptional.isPresent()) {
	        return ResponseEntity.ok(estudiantesOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}