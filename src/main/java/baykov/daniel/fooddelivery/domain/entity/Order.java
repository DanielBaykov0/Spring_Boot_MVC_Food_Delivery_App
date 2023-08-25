package baykov.daniel.fooddelivery.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private BigDecimal price;

    private String comment;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    private LocalDateTime deliveredOn;

    @Column(nullable = false)
    private String contactPhoneNumber;

    @Column(nullable = false)
    private Boolean isDelivered;
}
