package com.example.book.security;
import java.nio.charset.StandardCharsets; import javax.crypto.SecretKey; import org.springframework.stereotype.Component; import io.jsonwebtoken.Jwts; import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
 private static final String SECRET="MySimpleDemoSecretKeyForJwtMicroservices2026";
 private final SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
 public String getUsername(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
}
