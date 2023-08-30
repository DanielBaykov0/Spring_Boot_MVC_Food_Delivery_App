package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.service.ProductService;
import baykov.daniel.fooddelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.*;

@Controller
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping
    public String getMenu(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute(COUNT_PRODUCTS, this.userService
                    .getUserByEmail(principal.getName())
                    .getCart()
                    .getProductsCount());
        }

        return "menu-categories";
    }

    @GetMapping("/{category}")
    public String getCategoryPage(@PathVariable String category, Model model, Principal principal) {
        model.addAttribute(CATEGORY, this.productService.findCategory(category));
        model.addAttribute(PRODUCTS, this.productService.getAllProductsByCategory(ProductCategoryEnum.valueOf(category)));

        if (principal != null) {
            model.addAttribute(COUNT_PRODUCTS, this.userService
                    .getUserByEmail(principal.getName())
                    .getCart()
                    .getProductsCount());
        }

        return "categories-page";
    }
}
