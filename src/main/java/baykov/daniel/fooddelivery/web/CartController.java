package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.service.CartService;
import baykov.daniel.fooddelivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/cart/add/{id}")
    public String addToCart(
            @PathVariable Long id,
            Principal principal) {
        String category = this.productService.getProductCategory(id);
        return "redirect:/menu" + category;
    }
}
