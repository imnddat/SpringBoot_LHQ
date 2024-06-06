package com.datnd.real_world_app.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.TokenPayLoad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    private final String secret = "DAT_DZ";

    public String generateToken(User user, Long expiredDate) {
        Map<String, Object> claims = new HashMap<>();
        TokenPayLoad tokenPayLoad = TokenPayLoad.builder().userId(user.getId()).email(user.getEmail()).build();
        claims.put("payload", tokenPayLoad);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}
