package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        model.addAttribute("user", this.userService.getUserByUsername(principal.getName()));
        return "user-profile";
    }
}
