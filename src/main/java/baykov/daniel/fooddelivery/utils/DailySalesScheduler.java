package baykov.daniel.fooddelivery.utils;

import baykov.daniel.fooddelivery.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static baykov.daniel.fooddelivery.constant.Messages.PROMOTIONS_CHANGED;

@Component
public class DailySalesScheduler {

    private final PromotionService promotionService;
    private final Logger LOGGER = LoggerFactory.getLogger(DailySalesScheduler.class);

    public DailySalesScheduler(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void changePromotions() {
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        this.promotionService.makePromotions(dayOfWeek);
        LOGGER.info(PROMOTIONS_CHANGED);
    }
}
