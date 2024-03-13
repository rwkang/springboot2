package shop.onekorea.springboot2.service.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Users1;
import shop.onekorea.springboot2.entity.secondary.Users2;
import shop.onekorea.springboot2.repository.primary.Users1Repository;
import shop.onekorea.springboot2.repository.secondary.Users2Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersDataCopyService {

    private final Users1Repository users1Repository;
    private final Users2Repository users2Repository;

    public List<Users2> copyService() {

        List<Users1> users1List = users1Repository.findAll();
        for (Users1 users1 : users1List) {
            Users2 users2 = new Users2();
            users2.setUsersUuid(users1.getUsersUuid());
            users2.setEmail(users1.getEmail());
            users2.setName(users1.getName());
            users2.setPassword(users1.getPassword());
            users2.setEmpNo(users1.getEmpNo());
            users2.setRole(users1.getRole());
            users2.setPhoneNo(users1.getPhoneNo());
            users2.setAddress(users1.getAddress());
            users2.setProfile(users1.getProfile());
            users2.setDeptCode(users1.getDeptCode());
            users2.setUpdatedAt(users1.getUpdatedAt());
            users2.setCreatedAt(users1.getCreatedAt());
            users2Repository.save(users2);
        }

        List<Users2> users2List = users2Repository.findAll();

        return users2List;
    }
}
