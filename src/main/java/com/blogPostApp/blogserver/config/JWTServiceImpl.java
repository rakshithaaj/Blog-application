package com.blogPostApp.blogserver.config;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.blogPostApp.blogserver.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTServiceImpl implements JWTService {
	
	private static final String SECRET = "yourSecretKey"; // Change this
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    @Override
    public String createToken1(User user) {
        Date now = new Date(0);
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        
        return Jwts.builder()
            .setSubject(user.getUsername())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }

    @Override
    public User parseToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();

        String username = claims.getSubject();
        return new User(0, username, null, null, username, username, username, null, null, null, null); // You may need to fetch user info from a database
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

	@Override
	public String createToken(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
