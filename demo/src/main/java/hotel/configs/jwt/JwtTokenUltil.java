package hotel.configs.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUltil {
    @Value("${secret}")
    private String secret;

    public static final long JWT_TOKEN_VALIDAITY = 86400;

    private Claims getAllClaimsFromToken(String token) {return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();}
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //lấy username
    public String getUsernameFromToken(String token) {return getAllClaimsFromToken(token).getSubject();}
    // lấy ngày hết hạn
    public Date getExpirationDateFromToken(String token) {return getAllClaimsFromToken(token).getExpiration();}
    //xác nhận token hết hạn ?
    public boolean isTokenExpired(String token) {return getExpirationDateFromToken(token).before(new Date());}
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        Date expiration = new Date(now + JWT_TOKEN_VALIDAITY * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    // create token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }
    // validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
