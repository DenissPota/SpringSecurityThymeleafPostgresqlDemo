package ee.moontego.crud.thymeleaf.service;

import ee.moontego.crud.thymeleaf.entity.Animal;
import ee.moontego.crud.thymeleaf.entity.AnimalRepository;
import ee.moontego.crud.thymeleaf.entity.AnimalType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> findAllAnimals() {
        return animalRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Optional<Animal> findAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
