package shop.onekorea.springboot2.entity.secondary;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "UserCantDelete")
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Builder
public class User2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userUuid; // [type]을 [UUID]로 줄 수 있지만, 반드시 [String]으로 주어야 한다.
    @Column(nullable = false, unique = true)
    private String email; // [username] : Spring Boot Security에서 사용하는 이름. [userid]가 아님에 주의.
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String empNo;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String phoneNo;
    private String address;
    private String profile;

    // A 부분. 3-1
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dept_code")
//    private Dept dept;
    private String deptCode;

    //    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS) // 2023.09.29 Conclusion. "생성" 시에만 "서버 컴퓨터 시간"을 사용하고,
//    @Column(updatable = false) // "update" 시에는 "수정 되도록" : 생략 가능 ∵) true가 디폴트 임.
    private LocalDateTime updatedAt;

    //    @Builder.Default
//    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS) // 2023.09.29 Conclusion. "생성" 시에만 "서버 컴퓨터 시간"을 사용하고,
//    @Column(updatable = false) // "update" 시에는 "수정 안 됨"
    private LocalDateTime createdAt;
//    private LocalDateTime createdAt=LocalDateTime.now();

//    public User1(String userUuid, String email, String name, String password, String empNo, String role, String phoneNo, String address, String profile,
//                 String deptCode, LocalDateTime updatedAt, LocalDateTime createdAt) {
//        this.userUuid = userUuid;
//        this.email = email;
//        this.name = name;
//        this.password = password;
////        this.password = password.passwordEncoder.encode(password);
//        this.empNo = empNo;
//        this.role = role;
//        this.phoneNo = phoneNo;
//        this.address = address;
//        this.profile = profile;
//
//        this.deptCode = deptCode;
//        // A 부분. 3-2
////        if (deptsCode != null) {
////            changeDept(dept);
////        }
//
//        this.updatedAt = updatedAt;
//        this.createdAt = createdAt;
//    }


}

