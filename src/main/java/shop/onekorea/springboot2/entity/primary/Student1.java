/**
 * QueryDSL 사용 실험을 위한 모델.
 * https://www.youtube.com/watch?v=pLfUiXbVOh8&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=8
 * author: 2023.12.02 by rwkang.
 */

package shop.onekorea.springboot2.entity.primary;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Student")
public class Student1 {
    @Id
    private Long studentId;
    private String name;
    private Integer age;

//    public Student(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//    }

}

