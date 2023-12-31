package baykov.daniel.fooddelivery.service;

import baykov.daniel.fooddelivery.domain.entity.Product;
import baykov.daniel.fooddelivery.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import static baykov.daniel.fooddelivery.constant.Messages.*;

@Service
@AllArgsConstructor
public class PromotionService {

    private final ProductRepository productRepository;

    public void makePromotions(DayOfWeek dayOfWeek) {
        Product margherita = this.productRepository.findProductByName(MARGHERITA);
        Product pepperoni = this.productRepository.findProductByName(PEPPERONI);
        Product capricciosa = this.productRepository.findProductByName(CAPRICCIOSA);
        Product chickenBurger = this.productRepository.findProductByName(CHICKEN_BURGER);
        Product veganBurger = this.productRepository.findProductByName(VEGAN_BURGER);
        Product satoshi = this.productRepository.findProductByName(SATOSHI);
        Product sweetFries = this.productRepository.findProductByName(SWEET_FRIES);
        Product brownie = this.productRepository.findProductByName(BROWNIE);
        Product doughnuts = this.productRepository.findProductByName(DOUGHNUTS);
        Product mousse = this.productRepository.findProductByName(MOUSSE);

        switch (dayOfWeek) {
            case MONDAY -> monday(margherita, brownie, mousse);
            case TUESDAY -> tuesday(chickenBurger, sweetFries);
            case WEDNESDAY -> wednesday(veganBurger, pepperoni);
            case THURSDAY -> thursday(satoshi, doughnuts);
            case FRIDAY -> friday(capricciosa, margherita, pepperoni);
            case SATURDAY -> saturday(chickenBurger, satoshi, brownie, mousse);
        }
    }

    private void monday(Product margherita,
                        Product brownie,
                        Product mousse) {
        margherita.setPrice(BigDecimal.valueOf(6.99));
        this.productRepository.save(margherita);

        brownie.setPrice(BigDecimal.valueOf(3.59));
        this.productRepository.save(brownie);

        mousse.setPrice(BigDecimal.valueOf(2.99));
        this.productRepository.save(mousse);
    }

    private void tuesday(Product chickenBurger,
                         Product sweetFries) {
        chickenBurger.setPrice(BigDecimal.valueOf(9.99));
        this.productRepository.save(chickenBurger);

        sweetFries.setPrice(BigDecimal.valueOf(5.99));
        this.productRepository.save(sweetFries);
    }

    private void wednesday(Product veganBurger,
                           Product pepperoni) {
        veganBurger.setPrice(BigDecimal.valueOf(10.99));
        this.productRepository.save(veganBurger);

        pepperoni.setPrice(BigDecimal.valueOf(9.99));
        this.productRepository.save(pepperoni);
    }

    private void thursday(Product satoshi,
                          Product doughnuts) {
        satoshi.setPrice(BigDecimal.valueOf(12.99));
        this.productRepository.save(satoshi);

        doughnuts.setPrice(BigDecimal.valueOf(1.09));
        this.productRepository.save(doughnuts);
    }

    private void friday(Product capricciosa,
                        Product margherita,
                        Product pepperoni) {
        capricciosa.setPrice(BigDecimal.valueOf(7.99));
        this.productRepository.save(capricciosa);

        margherita.setPrice(BigDecimal.valueOf(5.99));
        this.productRepository.save(margherita);

        pepperoni.setPrice(BigDecimal.valueOf(8.99));
        this.productRepository.save(pepperoni);
    }

    private void saturday(Product chickenBurger,
                          Product satoshi,
                          Product brownie,
                          Product mousse) {
        chickenBurger.setPrice(BigDecimal.valueOf(9.99));
        this.productRepository.save(chickenBurger);

        satoshi.setPrice(BigDecimal.valueOf(13.99));
        this.productRepository.save(satoshi);

        brownie.setPrice(BigDecimal.valueOf(2.99));
        this.productRepository.save(brownie);

        mousse.setPrice(BigDecimal.valueOf(2.49));
        this.productRepository.save(mousse);
    }
}
