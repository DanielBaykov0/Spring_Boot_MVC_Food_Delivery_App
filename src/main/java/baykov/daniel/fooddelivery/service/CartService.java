package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.entity.Cart;
import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.repository.CartRepository;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import baykov.daniel.fooddelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Transactional
    public void addToCart(Long id, Principal principal) {
        User user = this.userRepository.findByUsername(principal.getName());
        Product product = this.productRepository.findProductById(id);

        user.getCart().addProduct(product);
        user.getCart().increaseProductsSum(product.getPrice());
    }

    @Transactional
    public void removeFromCart(Long id, Principal principal) {
        User user = this.userRepository.findByUsername(principal.getName());
        Product product = this.productRepository.findProductById(id);

        user.getCart().getProducts().remove(product);
        user.getCart().decreaseProductsSum(product.getPrice());
    }

    public Cart getNewCart() {
        Cart cart = new Cart();
        this.cartRepository.saveAndFlush(cart);
        return cart;
    }
}
