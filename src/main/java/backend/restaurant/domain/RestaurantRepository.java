package backend.restaurant.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
        
    Optional<Restaurant> findById(Long id);

}
