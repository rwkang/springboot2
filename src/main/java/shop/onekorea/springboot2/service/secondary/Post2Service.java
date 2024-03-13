package shop.onekorea.springboot2.service.secondary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.dto.Post2ResponseDto;
import shop.onekorea.springboot2.entity.secondary.Post2;
import shop.onekorea.springboot2.repository.secondary.Post2Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class Post2Service {
    private final Post2Repository post2Repository;

//    public PostService(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    public List<Post2> post2List() {
        // 1
//        List<Post2> post2List = new ArrayList<>();
        // 2
//        List<Post2> post2List = List.of(
//                new Post2(1L, UUID.randomUUID().toString(),"title1", "contents1", "author1", LocalDateTime.now()),
//                new Post2(2L, UUID.randomUUID().toString(),"title2", "contents2", "author2", LocalDateTime.now()),
//                new Post2(3L, UUID.randomUUID().toString(),"title3", "contents3", "author3", LocalDateTime.now())
//        );

        // 3
        List<Post2> post2List = post2Repository.findAll();

//        System.err.println(getInfo() + "post2List() 2");

        return post2List;
    }

    /** 2024.03.08 Conclusion. "조건 별" 검색에 의한 결과이므로, "Optional<>" 타입인가? */
    public Optional<Post2> findById(Long postId) {
        Optional<Post2> post2 = post2Repository.findById(postId);
        return post2;
    }

}
