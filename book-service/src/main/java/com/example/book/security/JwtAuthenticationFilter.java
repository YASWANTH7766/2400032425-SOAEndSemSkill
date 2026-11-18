package com.example.book.security;
import java.io.IOException; import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain; import jakarta.servlet.ServletException; import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 private final JwtUtil jwtUtil; public JwtAuthenticationFilter(JwtUtil jwtUtil){this.jwtUtil=jwtUtil;}
 @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException{
  String header=request.getHeader("Authorization");
  if(header!=null&&header.startsWith("Bearer "))try{String username=jwtUtil.getUsername(header.substring(7)); var auth=new UsernamePasswordAuthenticationToken(username,null,List.of()); auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); SecurityContextHolder.getContext().setAuthentication(auth);}catch(RuntimeException invalid){SecurityContextHolder.clearContext();}
  chain.doFilter(request,response);
 }
}
