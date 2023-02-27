package com.homey.homeyserver.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*
         * Todo :
         *  1. Authentication이 AuthenticationContext에 존재하면, 다음 필터로
         *  2. Jwt가 존재하고, expired or revoked되지 않고, 유효한 경우, 다음 필터로
         *  3. 1, 2에 해당하지 않는 경우, return
         *  */

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return;
        }

        String jwt = authorization.substring(7);

        String username = jwtService.extractUsername(jwt);

        //username Auth 여부 확인

        //jwt 유효성 확인


    }
}
