package com.datnd.real_world_app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.TokenPayLoad;
import com.datnd.real_world_app.repository.UserRepository;
import com.datnd.real_world_app.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String token = null;
        TokenPayLoad tokenPayLoad = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Token ")) {
            token = requestTokenHeader.substring(6).trim();
            try {
                tokenPayLoad = jwtTokenUtil.getTokenPayLoad(token);
            } catch (SignatureException e) {
                System.out.println("Invalid JWT signature");
            } catch (IllegalArgumentException ex) {
                System.out.println("Unable to get JWT");
            } catch (ExpiredJwtException ex) {
                System.out.println("Token has expired");
            }

        } else {
            System.out.println("JWT Token does not start with 'Token '");
        }

        if (tokenPayLoad != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userOptional = userRepository.findById(tokenPayLoad.getUserId());

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                // check token co hop le hay ko
                if (jwtTokenUtil.validate(token, user)) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                            user.getPassword(), new ArrayList<>());
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
