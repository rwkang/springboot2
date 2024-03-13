package shop.onekorea.springboot2.dto;

import org.springframework.stereotype.Component;
import shop.onekorea.springboot2.entity.primary.Post1;

import java.util.function.Function;

@Component
public class Post1ResponseMapper implements Function<Post1, Post1ResponseDto> { // 앞에 것이 Entity, 뒤에 것이 Out(Response)되는 타입.

    @Override
    public Post1ResponseDto apply(Post1 p) {
        Post1ResponseDto post1Response = Post1ResponseDto.builder()
                .id(p.getId())
                .postId(p.getPostId())
                .title(p.getTitle())
                //.password(p.getPassword())
                .contents(p.getContents())
                .author(p.getAuthor())
                .createdAt(p.getCreatedAt())
                .modifiedAt(p.getModifiedAt() == null ? p.getCreatedAt() : p.getModifiedAt())
                .build();
        return post1Response;
    }

}
