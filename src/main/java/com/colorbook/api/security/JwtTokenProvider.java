//package com.colorbook.api.security;
//
//import com.froghinter.frog.api.exception.FrogApiException;
//import com.froghinter.frog.api.model.LoginResponse;
//import com.froghinter.frog.common.constants.ResponseCode;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Slf4j
//@Component
//public class JwtTokenProvider {
//    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Value("${jwt.expired}")
//    private long jwtExpirationInMs;
//
//    @Value("${jwt.web-expired}")
//    private long webJwtExpirationInMs;
//
//    private final com.froghinter.frog.api.security.UserPrincipalDetailService userPrincipalDetailService;
//
//    public JwtTokenProvider(com.froghinter.frog.api.security.UserPrincipalDetailService userPrincipalDetailService) {
//        this.userPrincipalDetailService = userPrincipalDetailService;
//    }
//
//    public String generateToken(Authentication authentication) {
//        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .compact();
//    }
//
//    public String generateWebToken(Authentication authentication) {
//        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + webJwtExpirationInMs);
//
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .compact();
//    }
//
//    public String generateTemporaryToken(Authentication authentication) {
//        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + (1000 * 60 * 10));
//
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .compact();
//    }
//
//    public String generateRefreshWebToken(Authentication authentication) {
//        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + (webJwtExpirationInMs * 2));
//
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .claim("tokenType", "refresh")
//                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .compact();
//    }
//
//    public LoginResponse generateRefreshToAccessWebToken(String token) {
//
//        UserPrincipal userPrincipal = userPrincipalDetailService.loadUserByUsername(getUserNameFromRefreshJwt(token));
//
//        Date now = new Date();
//        Date refreshExpiryDate = new Date(now.getTime() + (webJwtExpirationInMs * 2));
//        Date expiryDate = new Date(now.getTime() + webJwtExpirationInMs);
//
//        return LoginResponse.builder()
//                .refreshToken(Jwts.builder()
//                        .setSubject(userPrincipal.getUsername())
//                        .setIssuedAt(now)
//                        .setExpiration(refreshExpiryDate)
//                        .claim("tokenType", "refresh")
//                        .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                        .compact())
//                .token(Jwts.builder()
//                        .setSubject(userPrincipal.getUsername())
//                        .setIssuedAt(now)
//                        .setExpiration(expiryDate)
//                        .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                        .compact())
//                .name(userPrincipal.getUsername())
//                .build();
//    }
//
//    public String getUserNameFromJwt(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        if (claims.get("tokenType") != null) {
//            throw new FrogApiException(ResponseCode.UNAUTHORIZED, "유효한 인증 정보가 아닙니다.", HttpStatus.UNAUTHORIZED);
//        }
//
//        return claims.getSubject();
//    }
//
//    private String getUserNameFromRefreshJwt(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        if (claims.get("tokenType") == null) {
//            throw new FrogApiException(ResponseCode.UNAUTHORIZED, "유효한 인증 정보가 아닙니다.", HttpStatus.UNAUTHORIZED);
//        }
//
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            logger.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            logger.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            logger.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            logger.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            logger.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//}
