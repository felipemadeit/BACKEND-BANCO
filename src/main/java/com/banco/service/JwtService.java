package com.banco.service;

import com.banco.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("M8dGrqPnqV96aHLTNkHtBTtbhwxn4P6JfYDymx77sTXTVS8yGQZdmUwfLDHZBZBrNvX3TQM4mxbTL4VzzsSRtq")
    private String secretKey;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserDni())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 HOURS
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private SecretKey getSigninKey () {
        byte[] keyBites = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBites);
    }

}
