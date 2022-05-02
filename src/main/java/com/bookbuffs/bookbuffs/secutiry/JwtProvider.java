package com.bookbuffs.bookbuffs.secutiry;

import com.bookbuffs.bookbuffs.excepetions.SpringBookbuffsException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;


@Service
public class JwtProvider {

    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;


    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
          //  InputStream resourceAsStream = getClass().getResourceAsStream("../../bookbuffsapp.jks");
            InputStream resourceAsStream = getClass().getResourceAsStream("resources/bookbuffsapp.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringBookbuffsException("Exception occurred while loading keystore", e);
        }

    }


    public String generateToken(Authentication authentication){
        User principal =   (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }


    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("bookbuffsapp", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringBookbuffsException("Exception occured while retrieving public key from keystore", e);
        }
    }


    /* Validate the token using the public key */
    public boolean validateToken(String jwt) throws KeyStoreException {
        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;

    }

    private PublicKey getPublicKey() throws KeyStoreException {
        try {
            return keyStore.getCertificate("bookbuffsapp").getPublicKey();
        }catch (KeyStoreException e){
            throw new KeyStoreException("Exception occured whule retrieving public key from keystore ");
        }
    }


    public String getUsernameFromJwt(String token) throws KeyStoreException {
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();

    }

}
