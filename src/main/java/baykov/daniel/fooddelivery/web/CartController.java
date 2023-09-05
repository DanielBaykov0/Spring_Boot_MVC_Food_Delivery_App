package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.service.CartService;
import baykov.daniel.fooddelivery.service.OrderService;
import baykov.daniel.fooddelivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.*;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping
    public String getCart(Model model, Principal principal) {
        model.addAttribute(CART_PRODUCTS, this.orderService.getProducts(principal.getName()));
        model.addAttribute(PRODUCTS_PRICE, this.orderService.getProductsPrice(principal.getName()));
        model.addAttribute(PRODUCTS_COUNT, this.orderService.getProducts(principal.getName()).size());
        return "order-cart";
    }

    @PatchMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, Principal principal) {
        this.cartService.addToCart(id, principal.getName());
        return "redirect:/menu/" + this.productService.getProductCategory(id);
    }

    @PatchMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, Principal principal) {
        this.cartService.removeFromCart(id, principal.getName());
        return "redirect:/cart";
    }
}
