package shop.onekorea.springboot2.service.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Dept1;
import shop.onekorea.springboot2.entity.secondary.Dept2;
import shop.onekorea.springboot2.repository.primary.Dept1Repository;
import shop.onekorea.springboot2.repository.secondary.Dept2Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptDataCopyService {

    private final Dept1Repository dept1Repository;
    private final Dept2Repository dept2Repository;

//    public List<Dept2> copyService() {
    public List<Dept2> copyService() {
//    public void copyDataDept() {
        List<Dept1> dept1List = dept1Repository.findAll();
        for (Dept1 dept1 : dept1List) {
            Dept2 dept2 = new Dept2();
            dept2.setDeptCode(dept1.getDeptCode());
            dept2.setDeptName(dept1.getDeptName());
            dept2.setUpdatedAt(dept1.getUpdatedAt());
            dept2.setCreatedAt(dept1.getCreatedAt());
            dept2Repository.save(dept2);
        }

        List<Dept2> dept2List = dept2Repository.findAll();

    return dept2List;

    }

}
