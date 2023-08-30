package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.service.OrderService;
import baykov.daniel.fooddelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.*;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/")
    public String getHome(Principal principal, Model model) {
        if (principal != null) {
            User user = this.userService.getUserByUsername(principal.getName());
            model.addAttribute(NAME, user.getUsername());
            model.addAttribute(ORDERS, orderService.getInProgressOrdersByUser(user));
            model.addAttribute(COUNT_PRODUCTS, user.getCart().getProductsCount());
        }

        return "index";
    }
}
