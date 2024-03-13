package shop.onekorea.springboot2.service.primary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.dto.Post1ResponseDto;
import shop.onekorea.springboot2.entity.primary.Post1;
import shop.onekorea.springboot2.entity.secondary.Post2;
import shop.onekorea.springboot2.repository.primary.Post1Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@RequiredArgsConstructor
@Service
public class Post1Service {
    private final Post1Repository post1Repository;

//    public PostService(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    public List<Post1> getPost1List() {
        // 1
//        List<Post> postList = new ArrayList<>();
        // 2
//        List<Post1> post1List = List.of(
//                new Post1(1, UUID.randomUUID().toString(),"title1", "contents1", "author1", LocalDateTime.now()),
//                new Post1(2, UUID.randomUUID().toString(),"title2", "contents2", "author2", LocalDateTime.now()),
//                new Post1(3, UUID.randomUUID().toString(),"title3", "contents3", "author3", LocalDateTime.now())
//        );

        // 3
        List<Post1> post1List = post1Repository.findAll();

//        System.err.println(getInfo() + "post1List() 2");

        return post1List;
    }

    /** 2024.03.08 Conclusion. "조건 별" 검색에 의한 결과이므로, "Optional<>" 타입인가? */
    public Optional<Post1> getPost1ById(Long postId) {
        Optional<Post1> post1Optional = post1Repository.findById(postId);
        System.err.println(getInfo() + " post1Optional: " + post1Optional); // Optional[Post1(id=1, postId=5189c999-1d4a-4ac8-8234-dc046de92124, password=$2a$10$YLkME2LwjKiUbt3fixr6OOlu5oweuIdxrP1IGbyBwI9G8Rv34lB0u, title=타이틀 0001, contents=컨텐츠 0001, author=rwkang@naver.com, createdAt=2024-03-09T04:14:34.064844, modifiedAt=2024-03-09T04:14:34.064844)]
        System.err.println(getInfo() + " post1Optional.getClass().getName(): " + post1Optional.getClass().getName()); // java.util.Optional
        System.err.println(getInfo() + " post1Optional.getClass().getTypeName(): " + post1Optional.getClass().getTypeName()); // java.util.Optional

        return post1Optional;
    }

    /* 2024.03.09 Conclusion. Post1ResponseDto 객체로 바로 받는 방법이 없네. */
//    public Optional<Post1> getPost1DtoById(Long postId) {
////    public Post1ResponseDto getPost1DtoById(Long postId) {
//        Optional<Post1> post1ResponseDto = post1Repository.findById(postId);
//        System.err.println(getInfo() + " post1ResponseDto: " + post1ResponseDto); // Optional[Post1(id=1, postId=5189c999-1d4a-4ac8-8234-dc046de92124, password=$2a$10$YLkME2LwjKiUbt3fixr6OOlu5oweuIdxrP1IGbyBwI9G8Rv34lB0u, title=타이틀 0001, contents=컨텐츠 0001, author=rwkang@naver.com, createdAt=2024-03-09T04:14:34.064844, modifiedAt=2024-03-09T04:14:34.064844)]
//        System.err.println(getInfo() + " post1ResponseDto.getClass().getName(): " + post1ResponseDto.getClass().getName()); // java.util.Optional
//        System.err.println(getInfo() + " post1ResponseDto.getClass().getTypeName(): " + post1ResponseDto.getClass().getTypeName()); // java.util.Optional
//
//        return post1ResponseDto;
//    }

    public Optional<Post1> getPost1ByTitle(String title) {
        Optional<Post1> post1Optional = post1Repository.findByTitle(title);
//        Optional<Post1> post1Optional = Optional.ofNullable(post1Repository.findByTitle(title));
        return post1Optional;
    }

//    public Post1 getOnlyPost1ByTitle(String title) {
//        Post1 post1 = post1Repository.findByTitle(title);
//        return post1;
//    }


}
