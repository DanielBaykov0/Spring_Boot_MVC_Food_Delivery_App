package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.constant.FoodCategoryEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(
            @PathVariable FoodCategoryEnum foodCategoryEnum,
            Model model) {
        model.addAttribute("category", foodCategoryEnum);
        return "category-page";
    }
}
