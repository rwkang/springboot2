package shop.onekorea.springboot2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.springboot2.dto.SubjectScoreResponseDto;
import shop.onekorea.springboot2.service.schedule.SubjectScoreService;

import java.util.List;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectScoreRestController {

    private final SubjectScoreService subjectScoreService;

    /*
    select st.student_id, st.name, st.age, st.subject, st.score
        from student st
        join score sc on st.student_id = sc.student_id
        where st.student_id = 1
        order by st.student_id;
     */

    @GetMapping(value = "/score/{studentId}") // "QueryDSL"을 사용, 위쪽 쿼리문 값 가져오기 실험
//    public ResponseEntity<Object> getScore(@PathVariable Long studentId) {
    public ResponseEntity<List<SubjectScoreResponseDto>> getScore(@PathVariable Long studentId) { // <= where st.student_id = 1
        System.err.println(getInfo() + " studentId: " + studentId);

        List<SubjectScoreResponseDto> subjectScoreList = subjectScoreService.findScoreByStudentId(studentId);

        return ResponseEntity.ok(subjectScoreList);

    }

}
