package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal) {
        model.addAttribute("cartProducts", this.orderService.getProducts(principal));
        return "order-cart";
    }
}
