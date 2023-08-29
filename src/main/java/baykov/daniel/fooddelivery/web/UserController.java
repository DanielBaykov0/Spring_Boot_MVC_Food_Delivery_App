package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.dto.binding.EditUserBindingDto;
import baykov.daniel.fooddelivery.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ModelAttribute("editedUser")
    public EditUserBindingDto initBindingDto() {
        return new EditUserBindingDto();
    }

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

    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "edit-user";
    }

    @PatchMapping("/edited/{id}")
    public String editedProduct(@PathVariable Long id,
                                @Valid EditUserBindingDto editUserBindingDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editedUser", editUserBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedUser", bindingResult);
            return "redirect:/users/edit/{id}";
        }

        this.userService.editUser(id, editUserBindingDto);
        return "redirect:/users/profile/{id}";
    }

    @GetMapping("/change/{id}")
    public String changeRole(@PathVariable Long id, Model model) {
        if (id != 1) {
            model.addAttribute("user", this.userService.getUserById(id));
            return "roles-change";
        }

        return "redirect:/users/all";
    }

    @PatchMapping("/roles/add/{id}/{name}")
    public String addRole(@PathVariable("id") Long id, @PathVariable("name") String role) {

        this.userService.addRole(id, role);
        return "redirect:/users/change/{id}";
    }

    @PatchMapping("/roles/remove/{id}/{name}")
    public String removeRole(@PathVariable Long id, @PathVariable("name") String role) {
        this.userService.removeRole(id, role);
        return "redirect:/users/change/{id}";
    }
}
