package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.UserToken;
import com.example.ShopForElectronicGoods.modelsDTO.RefreshTokenResponseDTO;
import com.example.ShopForElectronicGoods.repository.UserRepository;
import com.example.ShopForElectronicGoods.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public static final long JWT_ACCESS_TOKEN_EXPIRY = 300L;
    public static final long JWT_REFRESH_TOKEN_EXPIRY = 72000L;

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

        Integer userId = ((ApplicationUser) auth.getPrincipal()).getUser_id();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(JWT_REFRESH_TOKEN_EXPIRY))
                .subject(auth.getName())
                .claim("roles", scope)
                .claim("user_id", userId)
                .build();
        return  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public RefreshTokenResponseDTO responseRefreshToken(String token) throws ParseException {
        UserToken userRefreshToken = inValidDateToken(token);
        inValidDateUserTokens(userRefreshToken.getUser().getUser_id());
        Instant now = Instant.now();
        String expiryDate = userRefreshToken.getExpires_at();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expiryDate2 = sdf.parse(expiryDate);
        Instant expiryDateToken = expiryDate2.toInstant();
        if(now.isAfter(expiryDateToken)){
            throw new ApiRequestException("token is expiry", HttpStatus.UNAUTHORIZED);
        }

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(JWT_ACCESS_TOKEN_EXPIRY))
                .subject(userRefreshToken.getUser().getEmail())
                .claim("roles",userRefreshToken.getUser().getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" ")))
                .claim("user_id",userRefreshToken.getUser().getUser_id() )
                .build();

        String newToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        RefreshTokenResponseDTO responseDTO =
                RefreshTokenResponseDTO.builder()
                        .userId(userRefreshToken.getUser().getUser_id())
                        .userEmail(userRefreshToken.getUser().getEmail())
                        .token(newToken)
                        .refreshToken(userRefreshToken.getToken())
                        .refreshTokenExpiresAt(expiryDate2)
                        .build();

        return responseDTO;

    }


    public UserToken addToken(Integer userId, String token){
        Instant now = Instant.now();
        Instant expirationTime = now.plusSeconds(JWT_REFRESH_TOKEN_EXPIRY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = expirationTime.atZone(ZoneId.systemDefault());
        String formattedExpirationTime = zonedDateTime.format(formatter);
        UserToken userRefreshToken = new UserToken();
        ApplicationUser user = userRepository.findById(userId).orElseThrow();
        userRefreshToken.setUser(user);
        userRefreshToken.setToken(token);
        userRefreshToken.setExpires_at(formattedExpirationTime);
      return   userTokenRepository.save(userRefreshToken);
    }

    public UserToken getTokenByName(String token){

        UserToken u = userTokenRepository.findByToken(token);
        inValidDateToken(u.getToken());
        return u;
    }

    public UserToken inValidDateToken(String token){
        if (token == null || token.isEmpty()){
            throw new NullPointerException("Token not found");
        }
        UserToken userToken = userTokenRepository.findByToken(token);

        if(userToken == null ){
            throw new ApiRequestException("user Token not found", HttpStatus.NOT_FOUND);
        }
        ApplicationUser user = userToken.getUser();

        if(user == null){
            throw new ApiRequestException("user not found", HttpStatus.NOT_FOUND);
        }
        userToken.setIs_valid((byte) 0);
        return userTokenRepository.save(userToken);
    }

    public List<UserToken> inValidDateUserTokens(Integer userId){
        List<UserToken> userTokens = userTokenRepository.findByUserUserId(userId);
        if(userTokens.isEmpty()){
            throw new ApiRequestException("Not found userTokens by " + userId, HttpStatus.NOT_FOUND);
        }
        List<UserToken> inValidUserTokens = new ArrayList<>();

        for(UserToken u : userTokens){
            inValidUserTokens.add(inValidDateToken(u.getToken()));
        }
        return inValidUserTokens;
    }

}
