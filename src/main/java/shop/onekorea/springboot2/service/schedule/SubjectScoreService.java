package shop.onekorea.springboot2.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.dto.SubjectScoreResponseDto;
import shop.onekorea.springboot2.repository.primary.SubjectScore1Repository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectScoreService {

    private final SubjectScore1Repository subjectScoreRepository;

    public List<SubjectScoreResponseDto> findScoreByStudentId(Long studentId) {
        List<SubjectScoreResponseDto> subjectScoreResponseDtoList = subjectScoreRepository.findScoreByStudentId(studentId);

        return subjectScoreResponseDtoList;
    }

}
