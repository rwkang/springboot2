package shop.onekorea.springboot2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectScoreResponseDto {

    private Long studentId;
    private String name;
    private Integer age;
    private String subject;
    private Integer score;

}
