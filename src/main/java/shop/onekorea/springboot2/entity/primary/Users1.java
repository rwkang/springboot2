package shop.onekorea.springboot2.entity.primary;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * [Table User]를 [조회]하기 위해서, [UserDetailsService]라는 곳에서, [UserRepository]를 이용한다.
 * [UserDetails] 사용하기.
 * 1. 먼저 [public class User]까지 완성하여 서버를 실행하고 에러를 확인한 후,
 * 2. [public class User Implements UserDetails]를 추가하고,
 * 3. RB/[Implement methods]/Popup: Select Methods to implement/전체 메소드 선택 상태에서 OK 버튼 클릭으로 자동 메소드 생성한다.
 * 4. getUsername() 메소드에서, 실제로 사용할 컬럼을 넣어준다. 예) email 컬럼을 사용할 경우: [return getEmail()]
 * @author rwkang on 2023.10.31
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Builder
//@ToString(of = {"id", "users_uuid", "email", "name", "password", "phone_no", "emp_no", "role", "address", "profile", "dept_code", "updated_at", "created_at"})
//@ToString(of = {"id", "usersUuid", "email", "name", "password", "phoneNo", "empNo", "role", "address", "profile", "deptCode", "updatedAt", "createdAt"})
//public class Users implements UserDetails { /* [Security] 사용을 위해서는, [User] 테이블을 [UserDetails] 타입으로 만들어 주어야 한다. */
public class Users1 implements UserDetails {
//public class Users1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.UUID)
    private String usersUuid; // [type]을 [UUID]로 줄 수도 있지만, 반드시 [String]으로 주어야 한다.
    @Column(nullable = false, length = 100, unique = true)
    private String email; // [username] : Spring Boot Security]에서 사용하는 이름. [userid]가 아님에 주의...
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 100)
    private String password;
    //    @Column(nullable = false, length = 100)
    @Column(unique = true, nullable = false, length = 100)
    private String empNo;
    @Column(nullable = false, length = 100)
    private String role;
    @Column(nullable = false, length = 100)
    private String phoneNo;
    private String address;
    private String profile;

    // A 부분. 3-1
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dept_code")
//    private Dept dept;
    private String deptCode;

    //    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS) // 2023.09.29 Conclusion. "생성" 시에만 "서버 컴퓨터 시간"을 사용하고,
//    @Column(name = "updated_at", updatable = false)              //                        "update" 시에는 "수정 되도록" : 생략 가능 ∵) true가 디폴트 임.
    private LocalDateTime updatedAt;

    @Builder.Default
//    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS) // 2023.09.29 Conclusion. "생성" 시에만 "서버 컴퓨터 시간"을 사용하고,
//    @Column(name = "create_at", updatable = false)              //                        "update" 시에는 "수정 안 됨"
    private LocalDateTime createdAt=LocalDateTime.now();

//    public Users1(String usersUuid, String email, String name, String password, String empNo, String role, String phoneNo, String address, String profile,
//                 String deptCode, LocalDateTime updatedAt, LocalDateTime createdAt) {
//        this.usersUuid = usersUuid;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /** 관리자 계정 or 일반 사용자 계정 인지 [권한 체크]*/
        /** 여기서는 [권한 체크]는 빼고, [password] 확인만을 위한 실습이므로, [return null] 한다.*/
        return null;
    }

    @Override
    public String getUsername() { /* [username] : Spring Boot Security]에서 사용하는 이름. [userid]가 아님에 주의...*/
//        return null;
        return getEmail(); /* 여기 [springboot 프로젝트]에서는 [username]을 [email 컬럼]으로 사용하겠다는 의미...*/
    }

    /*
    아래 4개 메소드는 반드시 [return true]로 처리해야 한다. 1개 메소드라도,
    [return false] 하면, [로그인 팝업] 창에서 [index.html]로 넘어가질 못한다. 정확히는 [AuthController.java]로 넘어가질 못 한다.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; /* 이건 반드시 [true], [false]는 [로그인 안 됨]*/
//        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; /* 이건 반드시 [true], [false]는 [로그인 안 됨] */
//        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; /* 이건 반드시 [true], [false]는 [로그인 안 됨] */
//        return false;
    }

    @Override
    public boolean isEnabled() {
        return true; /* 이건 반드시 [true], [false]는 [로그인 안 됨] */
//        return false;
    }

}
