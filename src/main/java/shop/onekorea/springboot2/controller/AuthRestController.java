package shop.onekorea.springboot2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import shop.onekorea.springboot2.dto.AuthRequestDto;
//import shop.onekorea.springboot2.dto.AuthResponseDto;
import shop.onekorea.springboot2.dto.AuthRequestDto;
import shop.onekorea.springboot2.dto.AuthResponseDto;
import shop.onekorea.springboot2.entity.primary.Users1;
import shop.onekorea.springboot2.service.AuthService;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthService authService;

    /* 1. JWT 만 적용 전... */
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody Users1 users1) {
////    public AuthResponseDto authenticate(@RequestBody Users1 users1) {
//        System.err.println(getInfo() + ", users1: " + users1);
//
//        /** 참고: Users1 vs ResponseEntity<Users1> */
//
////        AuthResponseDto authResponseDto = authService.authenticateBeforeJwt(users1);
//        ResponseEntity<AuthResponseDto> responseEntityAuthResponseDto = ResponseEntity.ok(authService.authenticateBeforeJwt(users1));
//
////        System.err.println(getInfo() + " authResponseDto: " + authResponseDto);
////        System.err.println(getInfo() + " authResponseDto.getClass().getName(): " + authResponseDto.getClass().getName());
////        System.err.println(getInfo() + " authResponseDto.getClass().getTypeName(: " + authResponseDto.getClass().getTypeName());
//        System.err.println(getInfo() + " responseEntityAuthResponseDto: " + responseEntityAuthResponseDto);
//        System.err.println(getInfo() + " responseEntityAuthResponseDto.getClass().getName(): " + responseEntityAuthResponseDto.getClass().getName());
//        System.err.println(getInfo() + " responseEntityAuthResponseDto.getClass().getTypeName(): " + responseEntityAuthResponseDto.getClass().getTypeName());
//
//        return responseEntityAuthResponseDto;
////        return authResponseDto;
//    }

    /* 2. JWT + Refresh Token 같이 적용 후... */
    @PostMapping("/loginJwt")
    public ResponseEntity<AuthResponseDto> authenticationAfterJwt(@RequestBody AuthRequestDto login) {
        System.err.println(getInfo() + ", login: " + login);

        Users1 users1 = Users1.builder()
                .email(login.email())
                .password(login.password())
                .build();
        System.err.println(getInfo() + ", users1: " + users1);

        ResponseEntity<AuthResponseDto> responseEntityAuthResponseDto = ResponseEntity.ok(authService.authenticateAfterJwt(users1));

        return responseEntityAuthResponseDto;
    }

//    @PostMapping("/loginJwt")
////    public AuthResponseDto authenticationAfterJwt(@RequestBody AuthRequestDto login) {
//    public ResponseEntity<AuthResponseDto> authenticationAfterJwt(@RequestBody AuthRequestDto login) {
//        System.err.println(getInfo() + " login: " + login);
//        Users1 users1 = Users1.builder().email(login.email()).password(login.password()).build();
//        System.err.println(getInfo() + ", users1: " + users1);
//
//        /** 참고: Users1 vs ResponseEntity<Users1> */
//
//        AuthResponseDto authResponseDto = authService.authenticateAfterJwt(users1);
//        ResponseEntity<AuthResponseDto> responseEntityAuthResponseDto = ResponseEntity.ok(authService.authenticateAfterJwt(users1));
//
//        System.err.println(getInfo() + " authResponseDto: " + authResponseDto);
//        System.err.println(getInfo() + " authResponseDto.getClass().getName(): " + authResponseDto.getClass().getName());
//        System.err.println(getInfo() + " authResponseDto.getClass().getTypeName(: " + authResponseDto.getClass().getTypeName());
//        System.err.println(getInfo() + " responseEntityAuthResponseDto: " + responseEntityAuthResponseDto);
//        System.err.println(getInfo() + " responseEntityAuthResponseDto.getClass().getName(): " + responseEntityAuthResponseDto.getClass().getName());
//        System.err.println(getInfo() + " responseEntityAuthResponseDto.getClass().getTypeName(): " + responseEntityAuthResponseDto.getClass().getTypeName());
//
////        return authResponseDto;
//        return responseEntityAuthResponseDto;
//    }


    /*
    authority : 회원 가입 = 인가
    authentication : 로그인 = 인증
    auth = authority + authentication
     */

}

