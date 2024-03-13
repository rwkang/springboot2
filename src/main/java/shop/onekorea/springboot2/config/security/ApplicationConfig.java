package shop.onekorea.springboot2.config.security;

/**
 * [Spring Boot Security]에는 [SecurityFilterChain]과,
 *      [AuthenticationManager/AuthenticationProvider/UserDetailsService/PasswordEncoder] 이상 4가지 [Bean]을 정리해야 하는데,
 * 1.   [SecurityFilterChain]만 따로 [SecurityConfig.java]로 세팅하는 이유는,
 *      [SecurityFilterChain]만 추가로 [EnableWebSecurity] 어노테이션이 필요하기 때문이다.
 * 2.   [ApplicationConfig]에는 나머지 4개 [AuthenticationManager/AuthenticationProvider/UserDetailsService/PasswordEncoder]를 정리한다.
 * 여기서 중요한 것은,
 *      상기 4개 [Bean]을 [Spring Boot Security]에서 사용하는 [이름=타입]이라는 것이 중요하다. 즉,
 *      [PasswordEncoder]라는 [Bean]을 정리한다는 것은 [PasswordEncoder]라는 [타입]을 정의한다는 것이다.
 *      왜냐하면, [스프링 부트 시큐러티]가 [PasswordEncoder] 이 이름을 내부적으로 사용하기 때문이다.
 * 그 구조를 보면,
 *      [PasswordEncoder]에서 [Ctrl+H]로 [클래스 계층 구조]를 보면,
 *      [BCryptPasswordEncoder]를 [PasswordEncoder 인터페이스]로 구현한 것을 확인할 수 있다.
 *      참고로, 예전에는 [StandardPasswordEncoder] 구현체를 사용했었다. 현재는 Deprecated.
 *      다른 것들도 모두 같은 개념이다. 이것이 결국 [OOP] 개념인 것이다.
 * @Author rwkang on 2023.10.24
 */

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.onekorea.springboot2.repository.primary.Users1Repository;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private  final Users1Repository users1Repository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.err.println(getInfo() + ", new BCryptPasswordEncoder(): " + new BCryptPasswordEncoder());

        return new BCryptPasswordEncoder();
    }

    /*
    [UserDetailsService] 클래스를 찾아 들어가면, [loadUserByUsername()] 메소드가 딱 1개만 있으므로,
    아래와 같이 [함수형 프로그램=람다식]을 사용할 수 있다.
    그런데, UserDetailsService.loadUserByUsername 메소드 리턴 값이 다음 줄과 같이, [UserDetails] 이다.
    [UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;]
    그러므로 아래와 같이 [return (username) -> { return userRepository.findByEmail(username)...}, 이런 식이 되어야 한다.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        System.err.println(getInfo() + ", UserDetailsService.userDetailsService: ");

//        UserDetailsService userDetailsService = new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                System.err.println(getInfo() + ", username: " + username);
//                return usersRepository.findByEmail(username)
//                        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다!"));
//            }
//        };
//
//        System.err.println(getInfo() + ", userDetailsService: " + userDetailsService);
//        System.err.println(getInfo() + ", userDetailsService.getClass().getName(): " + userDetailsService.getClass().getName());
//        System.err.println(getInfo() + ", userDetailsService.toString(): " + userDetailsService.toString());
//
//        return userDetailsService;

//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                System.err.println(getInfo() + ", username: " + username);
//                return usersRepository.findByEmail(username)
//                        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다!"));
//            }
//        };

        return (username) -> {
            System.err.println(getInfo() + ", username: " + username);
            return users1Repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다!"));
                    /* [Users1Repository]에서 리턴 값을 [Optional<Users1>]로 했기 때문에,
                        위의 [.orElseThrow(() -> ~~] 이하 구문을 사용할 수 있음에 특히 주의...*/
        };
        // return Users;
    }

    /* 아래 이넘[authenticationProvider()]을 [SecurityConfig.java.SecurityFilterChain() 메소드]에 연결*/
    @Bean
    public AuthenticationProvider authenticationProvider() {
        System.err.println(getInfo() + ", authenticationProvider() 들어왔네!!!");

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        System.err.println(getInfo() + ", authProvider: " + authProvider);

        return authProvider;
    }

    /*
    아래 [AuthenticationManager] 클래스에 [로그인 정보]가 몽땅 들어 있다. [Context Holder] 라고도 한다???
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.err.println(getInfo() + ", authenticationManager!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.err.println(getInfo() + ", authenticationManager:" + config);

        return config.getAuthenticationManager();
    }

}

