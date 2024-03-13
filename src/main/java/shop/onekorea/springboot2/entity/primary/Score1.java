/**
 * QueryDSL 사용 실험을 위한 모델.
 * https://www.youtube.com/watch?v=pLfUiXbVOh8&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=8
 * author: 2023.12.02 by rwkang.
 */

package shop.onekorea.springboot2.entity.primary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Score")
public class Score1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;
    private String subject;
    private Integer score;
    private Long studentId;

//    public Score(String subject, Integer score, Long studentId) {
//        this.subject = subject;
//        this.score = score;
//        this.studentId = studentId;
//    }

}
