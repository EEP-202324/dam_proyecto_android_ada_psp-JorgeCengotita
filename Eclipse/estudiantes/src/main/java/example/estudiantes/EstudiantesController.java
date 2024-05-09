package example.estudiantes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@Repository
@RestController
@RequestMapping("/estudiantes")
class EstudiantesController {
	private final EstudiantesRepository estudiantesRepository;
	
	public EstudiantesController(EstudiantesRepository estudiantesRepository) {
	      this.estudiantesRepository = estudiantesRepository;
	   }

	@GetMapping("/{requestedId}")
	private ResponseEntity<Estudiantes> findById(@PathVariable Integer requestedId, Principal principal) {
		Estudiantes estudiantes = findEstudiantes(requestedId, principal);
	    if (estudiantes != null) {
	        return ResponseEntity.ok(estudiantes);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@PostMapping
	private ResponseEntity<Void> createEstudiantes(UriComponentsBuilder ucb, Principal principal) {
	    Estudiantes newEstudiantes = new Estudiantes(null, "Pedro", null, null, null, principal.getName());
	    Estudiantes savedEstudiantes = estudiantesRepository.save(newEstudiantes);
	    URI locationOfNewEstudiantes = ucb
	            .path("estudiantes/{id}")
	            .buildAndExpand(savedEstudiantes.id())
	            .toUri();
	    return ResponseEntity.created(locationOfNewEstudiantes).build();
	}
	
	@GetMapping
	private ResponseEntity<List<Estudiantes>> findAll(Pageable pageable, Principal principal) {
	    Page<Estudiantes> page = estudiantesRepository.findByOwner(principal.getName(),
	    		PageRequest.of(
	    		        pageable.getPageNumber(),
	    		        pageable.getPageSize(),
	    		        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre"))
	    		));
	    return ResponseEntity.ok(page.getContent());
	}
	
	@PutMapping("/{requestedId}")
	private ResponseEntity<Void> putEstudiantes(@PathVariable Integer requestedId, @RequestBody Estudiantes estudiantesUpdate, Principal principal) {
		Estudiantes estudiantes = findEstudiantes(requestedId, principal);
	    if (estudiantes != null) {
	        Estudiantes updatedEstudiantes = new Estudiantes(estudiantes.id(), estudiantesUpdate.nombre(), estudiantes.apellidos(), estudiantes.correo(), estudiantes.dni(), principal.getName());
	        estudiantesRepository.save(updatedEstudiantes);
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.notFound().build();
	}

	private Estudiantes findEstudiantes(Integer requestedId, Principal principal) {
	    return estudiantesRepository.findByIdAndOwner(requestedId, principal.getName());
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteEstudiantes(@PathVariable Integer id, Principal principal) {	    
	    if (estudiantesRepository.existsByIdAndOwner(id, principal.getName())) {
	        estudiantesRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}