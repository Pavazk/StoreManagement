package com.project.StoreManagement.services;

import com.project.StoreManagement.exceptions.AuthenticationFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.function.ThrowingBiFunction;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    private static final String SECRET_KEY = "9d0f5f4c62e7e94df04a4cbb9b54f2868e8432d01ec4c0afd1510b50bd49d30f98b657089c39273e4a06bd8d7cf7063bedf317e6c2a391cb9cbe1de209857811";
    private static final long accessTokenValidity = 60 * 60 * 1000;
    private static final String hashSession;
    static {
        hashSession = generateRandomHash();
    }

    public static String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts.builder().
                setClaims(extraClaims).
                setIssuer(hashSession).
                setSubject(user.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity)).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).
                compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) throws AuthenticationFailedException {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            if (System.currentTimeMillis() - claims.getIssuedAt().getTime() < accessTokenValidity) {
                String email = claims.getSubject();
                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    if (Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody().getSubject().equals(email)
                        && hashSession.equals(claims.getIssuer())) {
                        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
                    }
                }
            }
            throw new AuthenticationFailedException("Credenciales JWT invalidas");
        } catch (Throwable e) {
            throw new AuthenticationFailedException("Credenciales JWT invalidas");
        }
    }

    private static String generateRandomHash() {
        byte[] randomBytes = new byte[64];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
