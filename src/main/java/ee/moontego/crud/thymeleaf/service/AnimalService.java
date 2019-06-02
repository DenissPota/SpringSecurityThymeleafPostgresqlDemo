package ee.moontego.crud.thymeleaf.service;

import ee.moontego.crud.thymeleaf.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    List<Animal> findAllAnimals();
    Optional<Animal> findAnimalById(Long id);
    void addAnimal(Animal animal);
    void deleteAnimal(Long id);


}
