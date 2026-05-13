package az.ingress.oca46.MyFirstSpringProject.repository;

import az.ingress.oca46.MyFirstSpringProject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
 // Select * from users where username = ?
    Optional<Users> findByUsername(String username);
}
