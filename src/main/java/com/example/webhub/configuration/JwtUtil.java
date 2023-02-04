package com.example.webhub.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt_secret}")
    private String clientSecret;

    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withClaim("exp", expirationDate)
                .withClaim("iat", expirationDate)

                .withIssuedAt(new Date())
                .withIssuer("DevCloudy service")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(clientSecret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(clientSecret))
                .withSubject("User details")
                .withIssuer("DevCloudy service")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
