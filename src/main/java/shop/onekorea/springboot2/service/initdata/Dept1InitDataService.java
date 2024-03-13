package shop.onekorea.springboot2.service.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Dept1;
import shop.onekorea.springboot2.repository.primary.Dept1Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Dept1InitDataService {

    private final Dept1Repository dept1Repository;

    public List<Dept1> initDataAddService(int max) {

        List<Dept1> dept1List = new ArrayList<>();

        String[] dept1NameList = {"관리부", "영업부", "생산부", "자재부", "품질부", "생산 관리부", "기술부", "기획실", "업무부"};

        String suffix = "";
        for (int i = 1; i <= max; i++) {
            if (i == 0) {
                suffix = "0001";
            } else {
//                dept1CodeSuffix = "1" + i * 1000;
                suffix = String.valueOf(i * 1000 + 1);
            }
            Dept1 dept1 = new Dept1 (
                    (long) i,
            "1001" + suffix,
                    dept1NameList[i - 1],
                    LocalDateTime.now(),
                    LocalDateTime.now());
            dept1List.add(dept1);
        }

        dept1Repository.saveAll(dept1List);

        return dept1List;
    }

}
