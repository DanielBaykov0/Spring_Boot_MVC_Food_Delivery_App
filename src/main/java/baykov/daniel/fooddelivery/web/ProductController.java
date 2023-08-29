package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.domain.dto.binding.EditProductBindingDto;
import baykov.daniel.fooddelivery.domain.dto.binding.AddProductBindingDto;
import baykov.daniel.fooddelivery.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
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

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable
                                  String category,
                                  Model model) {
        model.addAttribute("category", this.productService.findCategory(category));
        model.addAttribute("products", this.productService.getAllProducts(ProductCategoryEnum.valueOf(category)));
        return "categories-page";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        model.addAttribute("product", this.productService.getProductById(productId));
        return "edit-product";
    }


    @PatchMapping("/products/edited/{id}")
    public String editedProduct(
            @PathVariable("id") Long productId,
            @Valid EditProductBindingDto editProductBindingDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editedProductDto", editProductBindingDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedProductDto", bindingResult);
            return "redirect:/products/edited/{id}";
        }

        this.productService.editProduct(productId, editProductBindingDto);
        return "redirect:/menu";
    }

    @GetMapping("/products/add")
    public String addProduct() {
        return "add-product";
    }

    @PostMapping("/products/add")
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

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        String category = this.productService.getProductCategory(id);
        this.productService.deleteProduct(id);
        return "redirect:/menu/" + category;
    }
}
