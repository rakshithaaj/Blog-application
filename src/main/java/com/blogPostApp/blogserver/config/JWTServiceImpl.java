package com.blogPostApp.blogserver.config;

import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService {

    private static final String SECRET = "yourSecretKey"; // Replace with a secure secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    @Override
    public String createToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(user.getUsername()) // You can include more user-related data here
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return token;
    }

    @Override
    public User parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject(); // Extract user-related data from claims
        // You may need to fetch additional user info from a database

        User user = new User();
        user.setUsername(username);
        // Populate other user-related fields here

        return user;
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
