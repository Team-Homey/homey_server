package com.homey.homeyserver.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.parserBuilder;

@Service
public class JwtService {
    /*
     * Todo :
     *  - Jwt 생성
     *  - Jwt로부터 Claims 추출
     *  - Jwt 유효성 검증
     *
     * */

   @Value("${SECRET_KEY}")
    private static String SECRET_KEY;

    //메서드 참조 표현식으로 function 객체를 반환한다. getSubject의 return type이 지네릭 타입으로 들어간 것??
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        // Todo : Jwt 생성해서 반환
    }

    public boolean isTokenValid(String token) {
        // Todo : 토큰의 exprired 여부, username이 일치하는 지 여부 반환
    }

    private Key getSigningKey() {
        // Todo : SECRET_KEY를 Key 객체로 변환해서 반환
    }




}
