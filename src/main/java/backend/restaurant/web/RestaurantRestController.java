package backend.restaurant.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.restaurant.domain.Restaurant;
import backend.restaurant.domain.RestaurantRepository;
import backend.restaurant.domain.FoodCategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantRepository repository;
    public RestaurantRestController(RestaurantRepository repository, FoodCategoryRepository cRepository) {
        this.repository = repository;
    }

    @GetMapping("/restaurants")
    public Iterable<Restaurant> getRestaurants() {
        log.info("Fetching all restaurants");
        return repository.findAll();
    }

    @PostMapping("/restaurants")
    public Restaurant newRestaurant(@RequestBody Restaurant newRestaurant) {
        log.info("Saving new restaurant: " + newRestaurant);
        return repository.save(newRestaurant);
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant editRestaurant(@RequestBody Restaurant editedRestaurant, @PathVariable Long id) {
        log.info("Editing restaurant: " + editedRestaurant);
        editedRestaurant.setId(id);
        return repository.save(editedRestaurant);
    }

    @DeleteMapping("/restaurants/{id}")
    public Iterable<Restaurant> deleteRestaurant(@PathVariable Long id) {
        log.info("Deleting restaurant, id = " + id);
        repository.deleteById(id);
        return repository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Optional<Restaurant> getRestaurant(@PathVariable Long id) {
        log.info("Finding restaurant, id = " + id);
        return repository.findById(id);
    }

}
