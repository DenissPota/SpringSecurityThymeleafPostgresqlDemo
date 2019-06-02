package ee.moontego.crud.thymeleaf.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByOrderByNameAsc();
    Optional<Animal> findById(Long id);
}
