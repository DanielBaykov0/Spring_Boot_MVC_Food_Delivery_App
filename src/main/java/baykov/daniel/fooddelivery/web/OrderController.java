package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.dto.binding.OrderBindingDto;
import baykov.daniel.fooddelivery.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @ModelAttribute("orderDto")
    public OrderBindingDto initBindingDto() {
        return new OrderBindingDto();
    }


    @GetMapping("/finalize")
    public String createOrder(Model model, Principal principal) {
        model.addAttribute("foodPrice", this.orderService.getProductsPrice(principal));
        model.addAttribute("countBoxes", this.orderService.getProducts(principal).size());
        return "finalize-order";
    }

    @GetMapping("/history")
    public String getOrdersHistory(Model model, Principal principal) {
        model.addAttribute("orders", this.orderService.getOrdersByUser(principal));
        return "orders-history-user";
    }

    @GetMapping("/details/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {
        model.addAttribute("order", this.orderService.getOrderById(id));
        return "order-details";
    }

    @GetMapping("/all/history")
    public String getAllOrders(Model model) {
        model.addAttribute("allOrders", this.orderService.getAllOrders());
        return "orders-history";
    }

    @PostMapping("/finalize")
    public String finalizeOrder(
            @Valid OrderBindingDto orderDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderDto", orderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDto", bindingResult);
            return "redirect:/orders/finalize";
        }

        this.orderService.makeOrder(orderDto, principal);
        return "redirect:/";
    }

    @PatchMapping("/finish/{id}")
    public String finishOrder(@PathVariable Long id) {
        this.orderService.finishOrder(id);
        return "redirect:/orders/all/history";
    }
}
