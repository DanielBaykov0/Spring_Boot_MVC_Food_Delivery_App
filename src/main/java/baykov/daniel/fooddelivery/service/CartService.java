package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.entity.Cart;
import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.domain.entity.User;
import baykov.daniel.fooddelivery.exception.ObjectNotFoundException;
import baykov.daniel.fooddelivery.repository.CartRepository;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import baykov.daniel.fooddelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.*;

@Service
@AllArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Transactional
    public void addToCart(Long id, String email) {
        User user = this.userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new ObjectNotFoundException(USER, EMAIL, email));
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(PRODUCT, ID, id));

        user.getCart().addProduct(product);
        user.getCart().increaseProductsSum(product.getPrice());
    }

    @Transactional
    public void removeFromCart(Long id, String email) {
        User user = this.userRepository.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new ObjectNotFoundException(USER, EMAIL, email));
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(PRODUCT, ID, id));

        user.getCart().getProducts().remove(product);
        user.getCart().decreaseProductsSum(product.getPrice());
    }

    public Cart getNewCart() {
        Cart cart = new Cart();
        this.cartRepository.saveAndFlush(cart);
        return cart;
    }
}
