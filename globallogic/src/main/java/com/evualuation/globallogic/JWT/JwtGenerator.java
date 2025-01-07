package com.evualuation.globallogic.JWT;

import com.evualuation.globallogic.exception.JwtProcessException;
import com.evualuation.globallogic.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtGenerator {

    private final byte[] secret = Base64.getDecoder().decode("SE9sYU1VbmRvMTIzNVByYWN0aWNlUGFyYUFwcm9iYXJMb2dpY2E=");

    public String generateJwt(User user) {
        Map<String, Object> userClaims = new LinkedHashMap<>();
        userClaims.put("uuid", user.getUuid().toString());
        userClaims.put("name", user.getName());
        userClaims.put("password", user.getPassword());
        userClaims.put("email", user.getEmail());
        userClaims.put("created", Date.from(Instant.now()).toString());
        userClaims.put("lastLogin", Date.from(Instant.now()).toString());
        userClaims.put("active", user.isActive());

        if (user.getPhones() != null && !user.getPhones().isEmpty()) {
            List<LinkedHashMap<String, Object>> phoneListClaims = user.getPhones().stream()
                    .map(phone -> {
                        LinkedHashMap<String, Object> phoneClaims = new LinkedHashMap<>();
                        phoneClaims.put("number", phone.getNumber());
                        phoneClaims.put("citycode", phone.getCitycode());
                        phoneClaims.put("contrycode", phone.getContrycode());
                        return phoneClaims;
                    })
                    .collect(Collectors.toList());

            userClaims.put("phones", phoneListClaims);
        }
        return Jwts.builder()
                .claims().add(userClaims).and()
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public Claims decodeJwt(String token) {
        try {
            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret)).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            throw new JwtProcessException("Unnable to read Token");
        }
    }
}
