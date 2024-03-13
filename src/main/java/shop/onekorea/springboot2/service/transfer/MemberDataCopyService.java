package shop.onekorea.springboot2.service.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Member1;
import shop.onekorea.springboot2.entity.secondary.Member2;
import shop.onekorea.springboot2.repository.primary.Member1Repository;
import shop.onekorea.springboot2.repository.secondary.Member2Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberDataCopyService {

    private final Member1Repository member1Repository;
    private final Member2Repository member2Repository;

    public List<Member2> copyService() {

        List<Member1> member1List = member1Repository.findAll();
        for (Member1 member1 : member1List) {
            Member2 member2 = new Member2();
            member2.setUserUuid(member1.getUserUuid());
            member2.setEmail(member1.getEmail());
            member2.setName(member1.getName());
            member2.setPassword(member1.getPassword());
            member2.setEmpNo(member1.getEmpNo());
            member2.setRole(member1.getRole());
            member2.setPhoneNo(member1.getPhoneNo());
            member2.setAddress(member1.getAddress());
            member2.setProfile(member1.getProfile());
            member2.setDeptCode(member1.getDeptCode());
            member2.setUpdatedAt(member1.getUpdatedAt());
            member2.setCreatedAt(member1.getCreatedAt());
            member2Repository.save(member2);
        }

        List<Member2> member2List = member2Repository.findAll();

        return member2List;
    }
}
