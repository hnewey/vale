package org.newdevelopment.vale.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import org.newdevelopment.vale.data.model.UserAuth;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

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

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserAuth userAuth) {
        final long HALF_HOUR_IN_MILLIS = 1800000;

        //set expiration of token to be 30 minutes from now
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date expiration = new Date(t + (10 * HALF_HOUR_IN_MILLIS));

        return Jwts.builder().setSubject(userAuth.getUsername()).setExpiration(expiration).signWith(secretKey).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true; //parsing passed. Trust the token
        } catch (JwtException e) {
            return false; //don't trust the token
        }
    }

    public String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTDecodeException exception) {
            //Invalid token
        }
        return null;
    }
}
