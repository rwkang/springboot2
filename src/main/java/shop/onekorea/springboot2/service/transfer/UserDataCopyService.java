package shop.onekorea.springboot2.service.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.User1;
import shop.onekorea.springboot2.entity.secondary.User2;
import shop.onekorea.springboot2.repository.primary.User1Repository;
import shop.onekorea.springboot2.repository.secondary.User2Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDataCopyService {

    private final User1Repository user1Repository;
    private final User2Repository user2Repository;

    public List<User2> copyService() {

        List<User1> user1List = user1Repository.findAll();
        for (User1 user1 : user1List) {
            User2 user2 = new User2();
            user2.setUserUuid(user1.getUserUuid());
            user2.setEmail(user1.getEmail());
            user2.setName(user1.getName());
            user2.setPassword(user1.getPassword());
            user2.setEmpNo(user1.getEmpNo());
            user2.setRole(user1.getRole());
            user2.setPhoneNo(user1.getPhoneNo());
            user2.setAddress(user1.getAddress());
            user2.setProfile(user1.getProfile());
            user2.setDeptCode(user1.getDeptCode());
            user2.setUpdatedAt(user1.getUpdatedAt());
            user2.setCreatedAt(user1.getCreatedAt());
            user2Repository.save(user2);
        }

        List<User2> user2List = user2Repository.findAll();

        return user2List;
    }
}
