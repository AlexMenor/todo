package com.pantala.todo.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JwtService {
    private final Algorithm algorithm = Algorithm.HMAC256("todo");
    private final int minutesToExpire = 3000;

    private final JWTVerifier verifier = JWT.require(algorithm).build();

    private static final String userIdClaimKey = "userId";

    public String generateJwt(String userId) {

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutesToExpire);
        Date expirationDate = now.getTime();

        return JWT.create().withClaim(userIdClaimKey, userId)
                .withExpiresAt(expirationDate)
                .sign(this.algorithm);
    }

    public String verifyJwt(String token) throws JWTVerificationException {
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim(userIdClaimKey).asString();
    }
}
