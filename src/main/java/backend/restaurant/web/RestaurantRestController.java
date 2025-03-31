package backend.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.restaurant.domain.Restaurant;
import backend.restaurant.domain.RestaurantRepository;

@RestController
@RequestMapping("/api") 
public class RestaurantRestController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantRepository repository;

    public RestaurantRestController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/restaurants")
    public Iterable<Restaurant> getRestaurants() {
        log.info("Fetching all restaurants");
        return repository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        log.info("Finding restaurant, id = " + id);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> newRestaurant(@RequestBody Restaurant newRestaurant) {
        log.info("Saving new restaurant: " + newRestaurant);
        return ResponseEntity.ok(repository.save(newRestaurant));
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> editRestaurant(@RequestBody Restaurant editedRestaurant, @PathVariable Long id) {
        log.info("Editing restaurant: " + editedRestaurant);
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        editedRestaurant.setId(id);
        return ResponseEntity.ok(repository.save(editedRestaurant));
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        log.info("Deleting restaurant, id = " + id);
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
