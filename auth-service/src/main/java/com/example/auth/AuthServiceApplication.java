package com.example.auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication extends SpringBootServletInitializer {
 public static void main(String[] args){SpringApplication.run(AuthServiceApplication.class,args);}
 @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder app){return app.sources(AuthServiceApplication.class);}
}
