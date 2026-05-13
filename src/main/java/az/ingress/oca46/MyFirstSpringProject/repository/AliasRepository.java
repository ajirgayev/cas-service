package az.ingress.oca46.MyFirstSpringProject.repository;

import az.ingress.oca46.MyFirstSpringProject.entity.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AliasRepository extends JpaRepository<Alias, Long> {
}
