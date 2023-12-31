package baykov.daniel.fooddelivery.repository;

import baykov.daniel.fooddelivery.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmailIgnoreCase(String email);

    User findUserById(Long id);
}
