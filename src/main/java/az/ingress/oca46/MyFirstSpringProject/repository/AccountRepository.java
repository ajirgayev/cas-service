package az.ingress.oca46.MyFirstSpringProject.repository;

import az.ingress.oca46.MyFirstSpringProject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
