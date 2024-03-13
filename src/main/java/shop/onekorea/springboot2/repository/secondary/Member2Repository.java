package shop.onekorea.springboot2.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springboot2.entity.secondary.Member2;

public interface Member2Repository extends JpaRepository<Member2, Long> {
}
