package org.newdevelopment.vale.authorization;

import io.jsonwebtoken.JwtException;
import org.newdevelopment.vale.data.model.UserAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JWTAuthService {

    private static JWTAuthService instance;

    private JWTAuthService() {
    }

    public static JWTAuthService getInstance() {
        if (instance == null) {
            instance = new JWTAuthService();
        }
        return instance;
    }

    private SecretKey secretKey;

    public String generateToken(UserAuth userAuth) {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder().setSubject(userAuth.getUsername()).signWith(secretKey).compact();
    }

    public boolean validateToken(String token) {
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true; //parsing passed. Trust the token
        } catch (JwtException e) {
            return false; //don't trust the token
        }
    }
}
