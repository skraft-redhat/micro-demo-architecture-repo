package org.acme;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class GenerateToken {
    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token =
           Jwt.issuer("https://example.com/issuer") 
             .upn("jdoe@quarkus.io") 
             .groups(new HashSet<>(Arrays.asList("user", "admin"))) 
             .claim(Claims.birthdate.name(), "2001-07-13")
             .claim(Claims.aud.name(),"serviceA,serviceB")
             .expiresIn(3600)
           .sign();
        System.out.println(token);
    }
}