package example.estudiantes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

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
	
	@GetMapping
	private ResponseEntity<List<Estudiantes>> findAll(Pageable pageable) {
	    Page<Estudiantes> page = estudiantesRepository.findAll(
	    		PageRequest.of(
	    		        pageable.getPageNumber(),
	    		        pageable.getPageSize(),
	    		        pageable.getSortOr(Sort.by(Sort.Direction.DESC, "nombre"))
	    		));
	    return ResponseEntity.ok(page.getContent());
	}
}