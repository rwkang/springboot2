package shop.onekorea.springboot2.config.security.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.onekorea.springboot2.config.security.jwt.service.JwtService;
//import shop.onekorea.springboot.repository.secondary.TokenRepository;

import java.io.IOException;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // OncePerRequestFilter: 한 번만 동작

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
//    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String start = "Bearer"; // [~Request.http] 파일에서, "Authorization" 값을 줄 때, 따옴표["]가 첫글자에 있으면 안 됨.
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail; // 스프링 시큐러티 로직에서의 [username]

        System.err.println(getInfo() + " ==========================================================================");
        System.err.println(getInfo() + " authHeader: " + authHeader);
        if (authHeader != null) {
            System.err.println(getInfo() + " authHeader.getClass().getTypeName(): " + authHeader.getClass().getTypeName());
        }
        if (authHeader == null || !authHeader.startsWith(start)) {
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // 강의 원본.
            System.err.println(getInfo() + ", 리턴! request: " + request);
            System.err.println(getInfo() + ", 리턴! response: " + response);
            filterChain.doFilter(request, response);
//             void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException;
            return;
        }

        /* 2024.03.08 Conclusion. [~Request.http] 파일에서, "Authorization" 값을 줄 때, 따옴표["]가 첫글자에 있으면 안 됨.
        *  1. Project Folder.SpringBoot2 폴더에, [http-client.env.json] 파일 참조할 것.
        *  2. resources/http/~Request.http 파일 참조할 것.
        *     # 2023.12.12 Conclusion. 여기 바로 위 "Run with" 값을 반드시 [No Environment] => [prod] or [dev]로 변경해 준다.
        #       1. 실행 순서는 반드시 먼저, 아래 [3-1] or [3-2]를 제일 먼저 실행하여, [token] 값을 얻고,
        #       2. 그 [token] 값을 반드시 [http-client.env.json] 파일에 [token] 값으로 넣어 주어야 한다.
        *     # [~Request.http] 파일에서, "Authorization" 값을 줄 때, 따옴표["]가 첫글자에 있으면 안 됨.
         */
        if (authHeader.startsWith(start)) {
            System.err.println(getInfo() + ", authHeader 값은, 정확히 [Bearer]로 시작하네요!!!, " + authHeader);
        } else {
            System.err.println(getInfo() + ", authHeader 값은, 반드시 [Bearer]로 시작해야 합니다!!!, " + authHeader);
            String s = authHeader.substring(0, 7);
            System.err.println(getInfo() + ", s 값: " + s);
            return;
        }

        jwt = authHeader.substring(7);
        System.err.println(getInfo() + " jwt: " + jwt);
        userEmail = jwtService.extractUsername(jwt); // todo extract the userEmail from JWT token.
        System.err.println(getInfo() + ", userEmail: " + userEmail);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            System.err.println(getInfo() + " usersEmail: " + userEmail);
            System.err.println(getInfo() + " userDetails: " + userDetails);
//            boolean isTokenValid = tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
//            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

