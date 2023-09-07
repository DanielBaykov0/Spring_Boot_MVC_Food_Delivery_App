package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.entity.Cart;
import baykov.daniel.fooddelivery.repository.CartRepository;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import baykov.daniel.fooddelivery.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService(userRepository, productRepository, cartRepository);
    }

    @Test
    void testSaveCart() {
        cartService.getNewCart();
        verify(cartRepository).saveAndFlush(any());
    }

    @Test
    void testReturnedCart() {
        Cart newCart = cartService.getNewCart();
        Assertions.assertEquals(0, newCart.getProducts().size());
        Assertions.assertEquals(BigDecimal.ZERO, newCart.getProductsSum());
    }

}
