package shop.onekorea.springboot2.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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


//import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import shop.onekorea.springboot2.config.security.jwt.filter.JwtAuthenticationFilter;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

//@Configurable 이건 안 됨...
@Configuration
@EnableWebSecurity /* SecurityFilterChain() 메소드 사용을 위한 어노테이션 */
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter; /* [JWT]를 사용을 위한 JwtAuthenticationFilter.java 파일 추가 후. */

    /*
    아래 부분 해석을 하자면,
    [.authorizeHttpRequests((authorizeRequest) -> authorizeRequest] : 여기는 요청 들어 온 [url]을 뜻 한다.
        .requestMatchers("auth/**").permitAll().anyRequest().authenticated()) : 여기는,
        ["auth/**"]로 들어 오는 것은 모두 허용하고, 그 외 나머지는 모두 [로그인] 한 후에 사용하도록 한다.
    [.authenticationProvider(authenticationProvider)] : 그 [인증 방식]으로는,
    [ApplicationConfig.authenticationProvider()] 여기 메소드에서 세팅한 것처럼,
    [DaoAuthenticationProvider]를 통해, [UserDetailsService]를 거쳐, [UserDetails]를 거쳐, 최종적으로 [User] 테이블까지 가서,
    사용자 정보를 확인한다는 것으로 해석할 수 있다.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.err.println(getInfo() + " Security FilterChain: http" + http.sessionManagement());
        System.err.println(getInfo() + " Security FilterChain: http");

        /** 아래 [.authorizeHttpRequests() 메소드란 결국, 클라이언트에서 보내는, [Request url] 정보이며, 이것을 분석하여 처리. */
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
//                                new AntPathRequestMatcher("/posts1/**"),
                                new AntPathRequestMatcher("/auth/**"))
                        .permitAll()
                        .anyRequest().authenticated())
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) /* [JWT]를 사용하므로써, [session] 사용 안 하도록, 여기서 [STATELESS] 세팅 함 */

//                .authenticationProvider(authenticationProvider).httpBasic(Customizer.withDefaults()); /* .httpBasic(Customizer.withDefaults()); => [JWT]를 사용하므로써, 이것도 사용 안 함 */

                .authenticationProvider(authenticationProvider) /* .httpBasic(Customizer.withDefaults()); => [JWT]를 사용하므로써, 이것도 사용 안 함 */
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); /* JWT Authentication Policy */

        /*
        위단 끝 부분 [.httpBasic()] 여기는, [로그인 창을 팝업] 형식으로 띄우게 된다.
         */

        System.err.println(getInfo() + ", 리턴 전 Security FilterChain: http: " + http);
        return http.build();

//        http
//                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
//                        .anyRequest().authenticated())
//                        .httpBasic(Customizer.withDefaults());

//        http.httpBasic(HttpBasicConfigurer::disable)
//                .csrf(CsrfConfigurer::disable)
//                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers("/actuator/**", "/swagger-ui/**", "/sign/**",
//                                        "/api-docs/swagger-config", "/sign-in", "/sign-up").permitAll()
//                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                )
//                .exceptionHandling(authenticationManager -> authenticationManager
//                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                        .accessDeniedHandler(new CustomAccessDeniedHandler()))
//                .addFilterBefore(new JwtAuthenticationFilter(this.userDetailsService, this.jwtTokenResolver),
//                        UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
    }

}

