package ee.moontego.crud.thymeleaf.controller;

import ee.moontego.crud.thymeleaf.entity.Animal;
import ee.moontego.crud.thymeleaf.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ShelterController {

    private final AnimalService animalService;

    public ShelterController(AnimalService animalService) {
        this.animalService = animalService;
    }


    @GetMapping(path = "/animals")
    public String findAllAnimals(Model theModel) {
        List<Animal> animals = animalService.findAllAnimals();
        theModel.addAttribute("animals", animals);
        return "list-animals";
    }

    @GetMapping(path = "/animals/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Animal animal = new Animal();
        theModel.addAttribute("animal", animal);
        return "animal-form";
    }

    @GetMapping(path = "/animals/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
        Optional<Animal> animal = animalService.findAnimalById((long) id);
        theModel.addAttribute("animal", animal.get());
        return "animal-form";
    }

    @PostMapping(path = "/animals/save")
    public String addAnimal(@ModelAttribute("animal") Animal animal) {
        animalService.addAnimal(animal);
        return "redirect:/animals";
    }

    @GetMapping(path = "/animals/delete")
    public String deleteAnimal(@RequestParam("id") int id) {
        animalService.deleteAnimal((long) id);
        return "redirect:/animals";
    }
}
