package shop.onekorea.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.config.security.jwt.service.JwtService;
import shop.onekorea.springboot2.dto.AuthResponseDto;
import shop.onekorea.springboot2.entity.primary.Users1;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;
//import static shop.onekorea.springboot2.entity.primary.QUsers1.users1;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

//    /* 1. JWT 만 적용... */
//    public AuthResponseDto authenticateBeforeJwt(Users1 users1) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users1.getEmail(), users1.getPassword()));
//
//        AuthResponseDto authResponseDto = new AuthResponseDto(jwtService.generateToken(users1));
//
//        /* 로그인 이후 정상 로직 또는 로그인 실패시 예외 발생. 여기서는 아래와 같이 심플하게 [users1]을 리턴하게 했다.*/
//
//        return authResponseDto;
//    }

    /* 2. JWT + Refresh Token 같이 적용 후... */
    public AuthResponseDto authenticateAfterJwt(Users1 users1) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users1.getEmail(), users1.getPassword()));
        AuthResponseDto authResponseDto = new AuthResponseDto(jwtService.generateToken(users1));

        /* 로그인 이후 정상 로직 또는 로그인 실패시 예외 발생, 여기서는 아래와 같이 심플하게 [users1]을 리턴하게 했다.*/

        System.err.println(getInfo() + ", authResponseDto: " + authResponseDto);
        return authResponseDto;
    }

}
