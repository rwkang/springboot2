package shop.onekorea.springboot2.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springboot2.entity.primary.Users1;

import java.util.Optional;

public interface Users1Repository extends JpaRepository<Users1, Long> {

    public Optional<Users1> findByEmail(String email);

}
