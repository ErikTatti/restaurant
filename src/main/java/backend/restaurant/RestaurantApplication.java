package backend.restaurant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.restaurant.domain.AppUser;
import backend.restaurant.domain.AppUserRepository;
import backend.restaurant.domain.FoodCategory;
import backend.restaurant.domain.FoodCategoryRepository;
import backend.restaurant.domain.Restaurant;
import backend.restaurant.domain.RestaurantRepository;


@SpringBootApplication
public class RestaurantApplication {

	private static final Logger log = LoggerFactory.getLogger(RestaurantApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(RestaurantRepository repository, FoodCategoryRepository cRepository,
			AppUserRepository urepository) {
		return (args) -> {
			log.info("Save a couple of food categories");

			FoodCategory category1 = new FoodCategory("Italian");
			FoodCategory category2 = new FoodCategory("Fast Food");
			FoodCategory category3 = new FoodCategory("Asian");
			FoodCategory category4 = new FoodCategory("Fine Dining");
			FoodCategory category5 = new FoodCategory("Finnish");

			cRepository.save(category1);
			cRepository.save(category2);
			cRepository.save(category3);
			cRepository.save(category4);
			cRepository.save(category5);

			log.info("Save a couple of restaurants");

			repository.save(new Restaurant("PizzaHut", "Tornikuja 1B, 00520 Helsinki", "€€", 2, "Hieaman ylihintaista", category1));
			repository.save(new Restaurant("Hesburger", "Veturitie 11 A-4020, 00520 Helsinki", "€", 4, "Nopea palvelu ja erinomainen sijainti", category2));
			repository.save(new Restaurant("Nanapo Sushi", "Fredrikinkatu 46, 00100 Helsinki", "€€", 5, "Laaja ja tuore valikoima", category3));
			repository.save(new Restaurant("Ravintola Olo", "Pohjoisesplanadi 5, 00170 Helsinki", "€€€€", 1, "Hienostunut ja tyylikäs", category4));
			repository.save(new Restaurant("Ravintola Kuu", "Töölönkatu 27, 00260 Helsinki", "€€€", 3, "Perinteinen ja laadukas", category5));
			repository.save(new Restaurant("Pystygrilli", "Kalevankatu 3, 00100 Helsinki", "€", 4, "Halpa ja hyvä", category2));

			log.info("Save a couple of users");

			AppUser user1 = new AppUser("user", "$2a$10$jq/LVhRyiJ8ULnWy/3rNw.GvDNg1lD6IyyrebznUp8AzpdcXA6.pS", "USER"); // password: 123
			AppUser user2 = new AppUser("user2", "$2a$10$Nnyj2zenvNrSD84PTa7gru14y5nxBSxUzqFFPmSIG8FNAP0eiXV5a", "USER"); // password: 1234
			AppUser user3 = new AppUser("admin", "$2a$10$nwzyWFWTHwAfsuNwA77TWOck3Np6wpbLRNdwgfpWgR.CyTYtA9XAS", "ADMIN"); // password: 12345
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);

			log.info("Printing all restaurants");
			for (Restaurant restaurant : repository.findAll()) {
				log.info(restaurant.toString());
			}
		};
	}
}
