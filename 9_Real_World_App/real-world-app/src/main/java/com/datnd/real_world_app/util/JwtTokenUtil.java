package com.datnd.real_world_app.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.TokenPayLoad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    // @Value("${JWT_SECRET_KEY}")
    // private String secret;

    // private Key getSigningKey() {
    //     byte[] keyBytes = Base64.getDecoder().decode(secret);
    //     return Keys.hmacShaKeyFor(keyBytes);
    // }

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(User user, long expiredDate) {
        Map<String, Object> claims = new HashMap<>();
        TokenPayLoad tokenPayLoad = TokenPayLoad.builder().userId(user.getId()).email(user.getEmail()).build();
        claims.put("payload", tokenPayLoad);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
                .signWith(key,SignatureAlgorithm.HS512).compact();
    }

    public TokenPayLoad getTokenPayLoad(String token) {

        return getClaimsFromToken(token, (Claims claim) -> {
            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
            return TokenPayLoad.builder().userId((int) mapResult.get("userId")).email((String) mapResult.get("email"))
                    .build();
        });
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);
    }

    public boolean validate(String token, User user) {
        TokenPayLoad tokenPayLoad = getTokenPayLoad(token);
        return tokenPayLoad.getUserId() == user.getId() && tokenPayLoad.getEmail().equals(user.getEmail())
                && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        // c1:
        // Date expiredDate = getClaimsFromToken(token, (Claims claim)->{
        // return claim.getExpiration();
        // });

        // c2:
        Date expiredDate = getClaimsFromToken(token, Claims::getExpiration);
        return expiredDate.before(new Date());
    }
}