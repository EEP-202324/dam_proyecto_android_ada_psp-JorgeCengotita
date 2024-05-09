package example.estudiantes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class EstudiantesRepositoryImpl implements EstudiantesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <S extends Estudiantes> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <S extends Estudiantes> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public Optional<Estudiantes> findById(Integer id) {
        Estudiantes estudiantes = entityManager.find(Estudiantes.class, id);
        return Optional.ofNullable(estudiantes);
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    @Override
    public Iterable<Estudiantes> findAll() {
        return entityManager.createQuery("SELECT e FROM Estudiantes e", Estudiantes.class).getResultList();
    }

    @Override
    public Iterable<Estudiantes> findAllById(Iterable<Integer> ids) {
        return entityManager.createQuery("SELECT e FROM Estudiantes e WHERE e.id IN :ids", Estudiantes.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT COUNT(e) FROM Estudiantes e").getSingleResult();
    }

    @Override
    public void deleteById(Integer id) {
        Estudiantes estudiantes = entityManager.find(Estudiantes.class, id);
        if (estudiantes != null) {
            entityManager.remove(estudiantes);
        }
    }

    @Override
    public void delete(Estudiantes entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void deleteAll(Iterable<? extends Estudiantes> entities) {
        for (Estudiantes entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Estudiantes").executeUpdate();
    }

    @Override
    public Estudiantes findByIdAndOwner(Integer id, String owner) {
        return entityManager.createQuery("SELECT e FROM Estudiantes e WHERE e.id = :id AND e.owner = :owner", Estudiantes.class)
                .setParameter("id", id)
                .setParameter("owner", owner)
                .getSingleResult();
    }

    @Override
    public boolean existsByIdAndOwner(Integer id, String owner) {
        return entityManager.createQuery("SELECT COUNT(e) FROM Estudiantes e WHERE e.id = :id AND e.owner = :owner", Long.class)
                .setParameter("id", id)
                .setParameter("owner", owner)
                .getSingleResult() > 0;
    }

    @Override
    public Page<Estudiantes> findByOwner(String owner, PageRequest pageRequest) {
        return PageableExecutionUtils.getPage(
            entityManager.createQuery("SELECT e FROM Estudiantes e WHERE e.owner = :owner", Estudiantes.class)
                .setParameter("owner", owner)
                .setFirstResult((int) pageRequest.getOffset())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList(),
            pageRequest,
            () -> countByOwner(owner));
    }

    private long countByOwner(String owner) {
        return entityManager.createQuery("SELECT COUNT(e) FROM Estudiantes e WHERE e.owner = :owner", Long.class)
                .setParameter("owner", owner)
                .getSingleResult();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Iterable<Estudiantes> findAll(Sort sort) {
        return entityManager.createQuery("SELECT e FROM Estudiantes e ORDER BY e.id " + sort.toString(), Estudiantes.class).getResultList();
    }

    @Override
    public Page<Estudiantes> findAll(Pageable pageable) {
        List<Estudiantes> estudiantes = entityManager.createQuery("SELECT e FROM Estudiantes e", Estudiantes.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(estudiantes, pageable, count());
    }
}
