package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        model.addAttribute("user", this.userService.getUserViewDtoByUsername(principal.getName()));
        return "user-profile";
    }

    @GetMapping("/profile/{id}")
    public String getProfileById(@PathVariable Long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "user-profile";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/change/{id}")
    public String changeRole(@PathVariable Long id, Model model) {
        if (id != 1) {
            model.addAttribute("user", this.userService.getUserById(id));
            return "roles-change";
        }

        return "redirect:/users/all";
    }
}
