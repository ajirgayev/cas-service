package az.ingress.oca46.MyFirstSpringProject.repository;

import az.ingress.oca46.MyFirstSpringProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
