package example.estudiantes;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/estudiantes")
class EstudiantesController {
	private final EstudiantesRepository estudiantesRepository;
	
	private EstudiantesController(EstudiantesRepository estudiantesRepository) {
	      this.estudiantesRepository = estudiantesRepository;
	   }

	@GetMapping("/{requestedId}")
	ResponseEntity<Estudiantes> findById(@PathVariable Integer requestedId) {
		Optional<Estudiantes> estudiantesOptional = estudiantesRepository.findById(requestedId);
		if (estudiantesOptional.isPresent()) {
	        return ResponseEntity.ok(estudiantesOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping
	private ResponseEntity<Void> createEstudiantes(@RequestBody Estudiantes newEstudiantesRequest, UriComponentsBuilder ucb) {
	   Estudiantes savedEstudiantes = estudiantesRepository.save(newEstudiantesRequest);
	   URI locationOfNewEstudiantes = ucb
	            .path("estudiantes/{id}")
	            .buildAndExpand(savedEstudiantes.id())
	            .toUri();
	   return ResponseEntity.created(locationOfNewEstudiantes).build();
	}
}