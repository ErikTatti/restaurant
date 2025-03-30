package backend.restaurant.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface FoodCategoryRepository extends CrudRepository<FoodCategory, Long> {

    List<FoodCategory> findByName(String name);
    }
    
