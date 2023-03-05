package com.homey.homeyserver.configuration;


import com.homey.homeyserver.service.JwtService;
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

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*
        *  1. Authentication이 AuthenticationContext에 존재하면, 다음 필터로
        *  2. jwt가 존재하지 않거나 유효하지 않으면, 다음 필터로
        *  3. jwt가 만료되었으면 다음 필터로
        *  4. 1, 2, 3에 해당하지 않는 경우 Authentication을 발급한다.
        * */

        String authorizationHeader = request.getHeader("Authorization");

        //토큰이 존재하지 않으면 Authentication을 부여할 수 없으므로 넘긴다.
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("token not exist");
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.substring(7);

        String username = jwtService.extractUsername(jwt);

        //이미 Authenticate됐거나, jwt에 subject가 제대로 들어있지 않았거나, 토큰이 만료된 경우 넘긴다.
        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null || jwtService.isExpired(jwt)) {
            System.out.println("invalid token");
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //jwt가 유효하면 Authentication을 ContextHolder에 저장한다.
        if (jwtService.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}