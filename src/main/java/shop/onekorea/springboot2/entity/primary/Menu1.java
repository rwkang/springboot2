package shop.onekorea.springboot2.entity.primary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Menu")
public class Menu1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String codeOrigin;
    private String language1;
    @Column(nullable = false)
    private String language2;
    private String language3;
    private String language4;
    private String explanation;
    @Column(length = 1)
    private String winType;
    private String winName;
    private String winParam;
    @Column(length = 1)
    private String isAccess;
    private String isUpdated;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
