package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    public static final long JWT_ACCESS_TOKEN_EXPIRY = 3600L;
    public static final long JWT_REFRESH_TOKEN_EXPIRY = 7200L;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    public String generateJwt(Authentication auth){
        Instant now = Instant.now();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Integer userId = ((ApplicationUser) auth.getPrincipal()).getUser_id();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(JWT_ACCESS_TOKEN_EXPIRY))
                .subject(auth.getName())
                .claim("roles", scope)
                .claim("user_id", userId)
                .build();

        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshJwt(Authentication auth){
        Instant now = Instant.now();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(JWT_REFRESH_TOKEN_EXPIRY))
                .subject(auth.getName())
                .claim("roles", scope)
                .build();
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
