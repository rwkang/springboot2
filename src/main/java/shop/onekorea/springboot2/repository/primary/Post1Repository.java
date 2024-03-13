package shop.onekorea.springboot2.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.onekorea.springboot2.entity.primary.Post1;

import java.util.Optional;

public interface Post1Repository extends JpaRepository<Post1, Long> {

//    public Post1 findByTitle(String title);
    Optional<Post1> findByTitle(String title);

}
