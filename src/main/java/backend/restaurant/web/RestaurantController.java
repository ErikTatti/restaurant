package backend.restaurant.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import backend.restaurant.domain.Restaurant;
import backend.restaurant.domain.RestaurantRepository;
import backend.restaurant.domain.AppUserRepository;
import backend.restaurant.domain.FoodCategoryRepository;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantRepository repository;
    private final FoodCategoryRepository cRepository;
    private final AppUserRepository userRepository;

    public RestaurantController(RestaurantRepository repository, FoodCategoryRepository cRepository, AppUserRepository userRepository) {
        this.repository = repository;
        this.cRepository = cRepository;
        this.userRepository = userRepository;

    }

    @RequestMapping(value="/login")
    public String login() {
        return "restaurantlist";
    }

    @GetMapping(value = { "/", "/restaurantlist" })
    public String restaurantList(Model model) {
        model.addAttribute("restaurants", repository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "restaurantlist";
    }

    @RequestMapping(value = "/add")
    public String addRestaurant(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("categories", cRepository.findAll());
        return "addrestaurant";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult bindingResult, Model model) {
        log.info("CONTROLLER: Save the restaurant - check validation of restaurant: " + restaurant);
        if (bindingResult.hasErrors()) {
            log.info("Some validation error happened, restaurant: " + restaurant);
            model.addAttribute("restaurant", restaurant);
            model.addAttribute("categories", cRepository.findAll());
            return "addrestaurant";
        }
        repository.save(restaurant);
        return "redirect:/restaurantlist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String showEditRestaurant(@PathVariable("id") Long restaurantId, Model model) {
        Restaurant restaurant = repository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID: " + restaurantId));
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("categories", cRepository.findAll()); 
        return "editrestaurant";
    }

    @PostMapping("/updateRestaurant")
    public String updateRestaurant(@ModelAttribute Restaurant restaurant) {
        repository.save(restaurant);
        return "redirect:/restaurantlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
    public String deleteRestaurant(@PathVariable("id") Long restaurantId, Model model) {
        repository.deleteById(restaurantId);
        return "redirect:/restaurantlist";
    }
    
}
