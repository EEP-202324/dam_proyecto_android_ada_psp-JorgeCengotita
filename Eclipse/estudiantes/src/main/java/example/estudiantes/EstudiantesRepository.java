package example.estudiantes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface EstudiantesRepository extends CrudRepository <Estudiantes, Integer>, PagingAndSortingRepository<Estudiantes, Integer>{
	
}
