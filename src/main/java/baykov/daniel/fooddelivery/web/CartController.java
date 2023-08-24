package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.service.CartService;
import baykov.daniel.fooddelivery.service.OrderService;
import baykov.daniel.fooddelivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal) {
        model.addAttribute("cartProducts", this.orderService.getProducts(principal));
        return "order-cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(
            @PathVariable Long id,
            Principal principal) {
        String category = this.productService.getProductCategory(id);
        return "redirect:/menu" + category;
    }
}
