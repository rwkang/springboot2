package shop.onekorea.springboot2.service.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Users1;
import shop.onekorea.springboot2.repository.primary.Users1Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static shop.onekorea.springboot2.util.DeptCode.getDeptCode;

@Service
@RequiredArgsConstructor
public class Users1InitDataService {

    private final PasswordEncoder passwordEncoder;
    private final Users1Repository users1Repository;

    public List<Users1> initDataAddService(int max) {

        List<Users1> users1List = new ArrayList<>();

        String iStr = "";
        String deptCode = "";
        for (int i = 1; i <= max; i++) {
            iStr = String.format("%04d", i);
            deptCode = getDeptCode(max, i);

            Users1 users1 = new Users1(
                    (long) i,
                    UUID.randomUUID().toString(),
                    "rwkang" + iStr + "@naver.com",
                    "rwkang" + iStr,
                    passwordEncoder.encode("0000"),
                    deptCode + iStr,
                    "ROLE_USER",
                    "010-3333-" + iStr,
                    "서울 강서구 방화동 " + iStr,
                    "프로파일 " + iStr,
                    deptCode,
                    LocalDateTime.now(),
                    LocalDateTime.now());
            users1List.add(users1);
        }

        users1Repository.saveAll(users1List);

        return users1List;

    }

}
