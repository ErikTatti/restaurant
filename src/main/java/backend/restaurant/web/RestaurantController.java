package backend.restaurant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestaurantController {

    @RequestMapping("/main")
    @ResponseBody
    public String returnMessage() {
        return "Hello World!!";
    }

    @RequestMapping("/restaurantlist")
    public String restaurantList() {
        return "restaurantlist";
    }


}
