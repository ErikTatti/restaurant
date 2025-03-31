package backend.restaurant.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Sort;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
        
    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAll(Sort sort);

}
