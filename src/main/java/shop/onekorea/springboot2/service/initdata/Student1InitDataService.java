package shop.onekorea.springboot2.service.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Student1;
import shop.onekorea.springboot2.repository.primary.Student1Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class Student1InitDataService {

    private final Student1Repository student1Repository;

    public List<Student1> initDataAddService(int max) {
        List<Student1> student1List = new ArrayList<>();

        Random random = new Random();
        int age = random.nextInt(60 - 30) + 30; // from 30 to 60

        String iStr = "";
        for (int i = 1; i <= max; i++) {
            iStr = String.format("%04d", i);
            Student1 student1 = new Student1(
                (long) i,
                "rwkang" + iStr,
                age + i);
            student1List.add(student1);
        }

        student1Repository.saveAll(student1List);

        return student1List;
    }

}
