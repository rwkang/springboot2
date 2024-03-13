/*
2024.02.29 Conclusion. **ORA-00903:invalid table name**
**해당 이슈의 원인 중 하나는, 테이블명을 오라클 내에서 사용되는 예약어로 설정했기 때문이다.**
**즉, 오라클에서 사용되는 예약어의 경우 테이블명으로 사용할 수 없다.**
### **어떤 이름을 테이블명으로 만들 수 있을까?**
**이클립스 내에서 SQL문을 작성할 때, 아래의 사진에서 보듯이 다른 단어들과 다른 색(보라색)의 단어는 오라클의 예약어다.**
**즉 create, table, user, primary, key 등은 테이블명으로 사용할 수 없는 것이다.**
**이외의 단어 중에도 테이블 생성을 위해 create~ 문을 입력하고 테이블명에 입력한 단어가 앞의**
**create, table과 같은 색으로 표기될 경우는 불가한 테이블명이라 생각하면 된다.**

create table user 는 불가 :
**게시판 홈페이지 등의 필요한 회원 목록 user table 유저테이블을 만들고 싶을 때,**
**user_t 또는 usertable 등의 단어로 테이블을 만들 수 있다.**
**즉, user 자체를 사용할 수는 없으나 뒤에 다른 단어, 언더바(_) 등을 덧붙여 테이블을 만들어 주면 된다.**
 */

package shop.onekorea.springboot2.entity.primary;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "UserCantDelete") /* 2024.02.29 Conclusion. **ORA-00903:invalid table name** : Oracle오라클 예약어.*/
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User1 {

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
