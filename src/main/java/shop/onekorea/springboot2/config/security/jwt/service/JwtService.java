package shop.onekorea.springboot2.config.security.jwt.service;

/**
 * @See : https://github.com/ohhoonim/springboot3-demo/blob/main/src/main/java/dev/ohhoonim/demo/config/service/JwtService.java
 */

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

//    private static final String SECRET_KEY = "462d4a614e645266556a586e3272357538782f413f4428472b4b625065536856";

    public String extractUsername(String token) {
        System.err.println(getInfo() + ", token: " + token);
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        System.err.println(getInfo() + ", token: " + token);

        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        System.err.println(getInfo() + ", userDetails: " + userDetails);

        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        System.err.println(getInfo() + ", userDetails: " + userDetails + ", jwtExpiration: " + jwtExpiration);
//        userDetails: Users1(id=null, usersUuid=null, email=rwkang0002@naver.com, name=null, password=0000, empNo=null, role=null, phoneNo=null, address=null, profile=null, deptCode=null, updatedAt=null, createdAt=2024-03-09T05:14:31.246828400)

        String token = buildToken(extraClaims, userDetails, jwtExpiration);
        System.err.println(getInfo() + ", token: " + token);

        return token;

        // return buildToken(extraClaims, userDetails, jwtExpiration);
        // <=== 아래 내용을, 위와 같이 buildToken() 메소드로 빼 냄.
        // return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
        //         .setIssuedAt(new Date(System.currentTimeMillis()))
        //         .setExpiration(new Date(System.currentTimeMillis()))
        //         .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        System.err.println(getInfo() + ", userDetails: " + userDetails + ", refreshExpiration: " + refreshExpiration);

        String refreshToken = buildToken(new HashMap<>(), userDetails, refreshExpiration);
        System.err.println(getInfo() + ", refreshToken: " + refreshToken);

        return refreshToken;
    }
//    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return buildToken(extraClaims, userDetails, refreshExpiration);
//    }

    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        System.err.println(getInfo() + ", userDetails: " + userDetails);

        String buildToken = Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))

        System.err.println(getInfo() + ", buildToken: " + buildToken);
        return buildToken;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        System.err.println(getInfo() + ", userDetails: " + userDetails);

        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//        return extractExpiration(token).before(new Date());
    }

    private boolean isTokenExpired(String token) {
        System.err.println(getInfo() + ", token: " + token);

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        System.err.println(getInfo() + ", token: " + token);

        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        System.err.println(getInfo() + ", token: " + token);

//        return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        System.err.println(getInfo() + ", Decoders.BASE64.decode(secretKey): " + Decoders.BASE64.decode(secretKey)); // [B@715d3f5a

        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        System.err.println(getInfo() + ", keyByte: " + keyByte); // [B@715d3f5a
        System.err.println(getInfo() + ", Keys.hmacShaKeyFor(keyByte): " + Keys.hmacShaKeyFor(keyByte)); // javax.crypto.spec.SecretKeySpec@fa77c8cd

        return Keys.hmacShaKeyFor(keyByte);
    }

}

