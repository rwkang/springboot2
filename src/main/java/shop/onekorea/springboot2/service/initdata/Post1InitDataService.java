package shop.onekorea.springboot2.service.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Post1;
import shop.onekorea.springboot2.repository.primary.Post1Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Post1InitDataService {

    private final PasswordEncoder passwordEncoder;
    private final Post1Repository post1Repository;

    public List<Post1> initDataAddService(int max) {

        List<Post1> post1List = new ArrayList<>();

        String iStr = "";
        for (int i = 1; i <= max; i++) {
            iStr = String.format("%04d", i);
            Post1 post1 = new Post1 (
                    (long) i,
                    UUID.randomUUID().toString(),
                    passwordEncoder.encode("0000"),
                    "타이틀 " + iStr,
                    "컨텐츠 " + iStr,
                    "rwkang@naver.com",
                    LocalDateTime.now(),
                    LocalDateTime.now());
            post1List.add(post1);
        }

        post1Repository.saveAll(post1List);

        return post1List;
    }

}
