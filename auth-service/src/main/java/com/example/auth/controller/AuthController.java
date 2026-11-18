package com.example.auth.controller;
import java.util.Map; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; import com.example.auth.model.User; import com.example.auth.service.AuthService;
@RestController @RequestMapping("/auth")
public class AuthController {
 private final AuthService authService; public AuthController(AuthService authService){this.authService=authService;}
 @PostMapping("/login") public ResponseEntity<Map<String,String>> login(@RequestBody User user){String token=authService.login(user); if(token==null)return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid username or password")); return ResponseEntity.ok(Map.of("token",token));}
}
