package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.dto.binding.AddProductBindingDto;
import baykov.daniel.fooddelivery.domain.dto.binding.EditProductBindingDto;
import baykov.daniel.fooddelivery.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.PRODUCT;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("productDto")
    public AddProductBindingDto initBindingDto() {
        return new AddProductBindingDto();
    }

    @ModelAttribute("editedProductDto")
    public EditProductBindingDto initEditProductBindingDto() {
        return new EditProductBindingDto();
    }

    @GetMapping("/add")
    public String getAddProduct() {
        return "add-product";
    }

    @PostMapping("/add")
    public String postAddProduct(@Valid AddProductBindingDto addProductBindingDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productDto", addProductBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDto", bindingResult);
            return "redirect:/products/add";
        }

        this.productService.addProduct(addProductBindingDto);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getEditProduct(@PathVariable Long id, Model model) {
        model.addAttribute(PRODUCT, this.productService.getProductById(id));
        return "edit-product";
    }

    @PatchMapping("/edited/{id}")
    public String editedProduct(
            @PathVariable Long id,
            @Valid EditProductBindingDto editProductBindingDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editedProductDto", editProductBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedProductDto", bindingResult);
            return "redirect:/products/edit/{id}";
        }

        this.productService.editProduct(id, editProductBindingDto);
        return "redirect:/menu/" + this.productService.getProductCategory(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        String category = this.productService.getProductCategory(id);
        this.productService.deleteProduct(id);
        return "redirect:/menu/" + category;
    }
}
