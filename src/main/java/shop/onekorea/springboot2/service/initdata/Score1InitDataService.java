package shop.onekorea.springboot2.service.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Score1;
import shop.onekorea.springboot2.repository.primary.SubjectScore1Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class Score1InitDataService {

    private final SubjectScore1Repository subjectScore1Repository;

    public List<Score1> initDataAddService(int max) {

        List<Score1> score1List = new ArrayList<>();

        Random random = new Random();
        int score = random.nextInt(100 -65) + 65; // from 65 to 100
        String subject;
        Long studentId;
        for (int i = 1; i <= max; i++) {
            if (i % 3 == 0) {
                subject = "math";
                studentId = 1L;
            } else if (i % 3 == 1) {
                subject = "korean";
                studentId = 2L;
            } else {
                subject = "english";
                studentId = 3L;
            }

            Score1 score1 = new Score1(
                    (long) i,
                    subject,
                    score,
                    studentId);
            score1List.add(score1);
        }

        subjectScore1Repository.saveAll(score1List);

        return score1List;
    }

}
