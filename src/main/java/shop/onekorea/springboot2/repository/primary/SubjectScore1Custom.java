package shop.onekorea.springboot2.repository.primary;

import shop.onekorea.springboot2.dto.SubjectScoreResponseDto;

import java.util.List;

/*
"Interface" 파일에는, Service 파일에서 사용할 method(findScoreByStudentId())를 [단지 정의만] 한다.
 */
public interface SubjectScore1Custom {
    List<SubjectScoreResponseDto> findScoreByStudentId(Long studentId);
}
