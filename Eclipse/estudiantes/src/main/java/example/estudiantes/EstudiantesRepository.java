package example.estudiantes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

interface EstudiantesRepository extends CrudRepository <Estudiantes, Integer>, PagingAndSortingRepository<Estudiantes, Integer>{
	Estudiantes findByIdAndOwner(Integer id, String owner);
	   Page<Estudiantes> findByOwner(String owner, PageRequest pageRequest);
}
