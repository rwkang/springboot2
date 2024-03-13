package shop.onekorea.springboot2.service.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Post1;
import shop.onekorea.springboot2.entity.secondary.Post2;
import shop.onekorea.springboot2.repository.primary.Post1Repository;
import shop.onekorea.springboot2.repository.secondary.Post2Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostDataCopyService {

    private final Post1Repository post1Repository;
    private final Post2Repository post2Repository;

    public List<Post2> copyService() {
        List<Post1> post1List = post1Repository.findAll();
        for (Post1 post1 : post1List) {
            Post2 post2 = new Post2();
            post2.setPostId(post1.getPostId());
            post2.setPassword(post1.getPassword());
            post2.setTitle(post1.getTitle());
            post2.setContents(post1.getContents());
            post2.setAuthor(post1.getAuthor());
            // post2.setModifiedAt(post1.getModifiedAt()); // "modified_at" 컬럼은, "null" 테스트를 위해, 일부러 치환을 안 한 것이고, 실 프로젝트에서는 모두 치환한다.
            post2.setCreatedAt(post1.getCreatedAt());
            post2Repository.save(post2);
        }

        List<Post2> post2List = post2Repository.findAll();

        return post2List;
    }
}
