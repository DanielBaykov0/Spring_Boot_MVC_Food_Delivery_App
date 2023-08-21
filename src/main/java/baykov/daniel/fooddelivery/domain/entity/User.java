package baykov.daniel.fooddelivery.domain.entity;

import baykov.daniel.fooddelivery.domain.constant.GenderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private GenderEnum genderEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private Set<Order> orders;
}
