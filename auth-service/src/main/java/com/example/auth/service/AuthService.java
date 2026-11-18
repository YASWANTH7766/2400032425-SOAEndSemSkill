package com.example.auth.service;
import org.springframework.stereotype.Service; import com.example.auth.model.User; import com.example.auth.security.JwtUtil;
@Service
public class AuthService {
 private final JwtUtil jwtUtil; public AuthService(JwtUtil jwtUtil){this.jwtUtil=jwtUtil;}
 public String login(User user){if(user!=null&&"student".equals(user.getUsername())&&"password123".equals(user.getPassword()))return jwtUtil.generateToken(user.getUsername()); return null;}
}
