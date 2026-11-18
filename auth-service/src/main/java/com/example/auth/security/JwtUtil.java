package com.example.auth.security;
import java.nio.charset.StandardCharsets; import java.util.Date; import javax.crypto.SecretKey;
import org.springframework.stereotype.Component; import io.jsonwebtoken.Jwts; import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
 // This demo secret must match the one in book-service. Keep secrets out of source in real systems.
 private static final String SECRET="MySimpleDemoSecretKeyForJwtMicroservices2026";
 private final SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
 public String generateToken(String username){Date now=new Date(); return Jwts.builder().subject(username).issuedAt(now).expiration(new Date(now.getTime()+3600000)).signWith(key).compact();}
}
