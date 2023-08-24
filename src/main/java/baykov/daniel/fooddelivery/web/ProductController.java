package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.domain.constant.ProductCategoryEnum;
import baykov.daniel.fooddelivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable
                                  ProductCategoryEnum category,
                                  Model model)
                                  /*@PageableDefault(
                                          page = 0,
                                          size = 5)
                                      Pageable pageable)*/ {

        model.addAttribute("category", category);
        model.addAttribute("products", this.productService.getAllProducts(category));

        return "categories-page";
    }
}
