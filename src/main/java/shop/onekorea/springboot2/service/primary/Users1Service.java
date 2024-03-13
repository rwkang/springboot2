package shop.onekorea.springboot2.service.primary;

import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.dto.*;
import shop.onekorea.springboot2.entity.primary.Users1;
import shop.onekorea.springboot2.repository.primary.Users1Repository;

import java.util.List;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Service
@RequiredArgsConstructor
public class Users1Service {

//    @Value("${application.security.jwt.expiration}")
//    private int jwtExpiration;
//    @Value("${application.security.jwt.refresh-token.expiration}")
//    private int refreshTokenExpiration;


    /**
     * "final" 이게 없으면, 치명적 에러 발생한다.
     * ERROR 95736 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet] :
     * Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [request processing failed:
     * java.lang.NullPointerException: Cannot invoke "shop.onekorea.springboot.service.UserService.userListService()" because "this.userService" is null] with root cause
     */
    private final Users1Repository users1Repository;

//    public UsersResponseDto findByEmailAndPassword(UsersRequestDto userRequestDto) {
//        System.err.println(getInfo() + ", userRequestDto.token(): " + userRequestDto.token());
//        System.err.println(getInfo() + ", userRequestDto.id(): " + userRequestDto.id());
//
////        String email = userRequestDto.email();
////        String password = userRequestDto.password();
////        User userSelected = userRepository.findByEmailAndPassword(email, password);
//
//        long id = userRequestDto.id();
//
//        Users userSelected = usersRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 없습니다!"));
//
//        // password 값 없애기.
//        userSelected.setPassword("");
//
//        // token 값 다시 넣기. => 사실 이건 다시 안 넣어도 되는디...
//        String token = userRequestDto.token();
//
//        // expiration 값 넣기.
//        int expiration = jwtExpiration; // 60 * 60 * 1000; // 1시간
//
//        // refreshExpiration 값 넣기.
//        int refreshExpiration = refreshTokenExpiration;
//
//        // UserResponseDto 세팅하기.
//        UsersResponseDto usersResponseDto = new UsersResponseDto(token, expiration, refreshExpiration, userSelected);
//        System.err.println(getInfo() + ", usersResponseDto : " + usersResponseDto);
//
//        return usersResponseDto;
//    }
//
//    public SignInResponseDto signInService(SignInRequestDto signInRequestDto) {
//        String email = signInRequestDto.getEmail();
//        String password = signInRequestDto.getPassword();
//
////        User userSelected = userRepository.findByUserIdAndPassword(userId, password);
////        Optional<User> userSelected = userRepository.findByEmailAndPassword(email, password);
//        Users userSelected = usersRepository.findByEmailAndPassword(email, password);
//
//        // password 값 없애기
//        userSelected.setPassword("");
//
//        String token = "token";
//        int expiration = 60 * 60 * 1000; // 1시간
//
//        // SignInResponseDto 만들기
//        SignInResponseDto signInResponseDto = new SignInResponseDto(token, expiration, userSelected);
//
//        System.err.println(getInfo() + ", signInResponseDto : " + signInResponseDto);
//
//        return signInResponseDto;
//    }

    public List<Users1> users1ListService() {
        System.err.println(getInfo());

        return users1Repository.findAll();
    }

}

