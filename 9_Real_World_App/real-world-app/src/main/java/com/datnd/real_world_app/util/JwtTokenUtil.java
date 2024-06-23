package com.datnd.real_world_app.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.datnd.real_world_app.entity.User;
import com.datnd.real_world_app.model.TokenPayLoad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    private final String secret = "QUAN_DZ";

    public String generateToken(User user, long expiredDate) {
        Map<String, Object> claims = new HashMap<>();
        TokenPayLoad tokenPayLoad = TokenPayLoad.builder().userId(user.getId()).email(user.getEmail()).build();
        claims.put("payload", tokenPayLoad);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public TokenPayLoad getTokenPayLoad(String token) {

        return getClaimsFromToken(token, (Claims claim) -> {
            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
            return TokenPayLoad.builder().userId((int) mapResult.get("userId")).email((String) mapResult.get("email"))
                    .build();
        });
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);
    }

    public boolean validate(String token, User user) {
        TokenPayLoad tokenPayLoad = getTokenPayLoad(token);
        return tokenPayLoad.getUserId() == user.getId() && tokenPayLoad.getEmail().equals(user.getEmail()) && !isTokenExpired(token);
        
    }

    private boolean isTokenExpired(String token) {
        //c1:
        // Date expiredDate = getClaimsFromToken(token, (Claims claim)->{
        //     return claim.getExpiration();
        // });
        
        //c2:
        Date expiredDate = getClaimsFromToken(token, Claims::getExpiration);
        return expiredDate.before(new Date());
    }
}
