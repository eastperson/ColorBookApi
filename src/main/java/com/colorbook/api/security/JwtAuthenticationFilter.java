//package com.colorbook.api.security;
//
//import com.froghinter.frog.api.exception.FrogApiException;
//import com.froghinter.frog.common.constants.ResponseCode;
//import com.froghinter.frog.common.entity.FrogMemberEntity;
//import com.froghinter.frog.common.entity.enums.ActiveType;
//import com.froghinter.frog.common.repository.FrogMemberRepository;
//import com.google.firebase.database.annotations.NotNull;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//// 해당 필터를 서비스화 하면 jwt 두번 찌름
//// @Service
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    private final UserPrincipalDetailService userPrincipalDetailService;
//
//    private final FrogMemberRepository frogMemberRepository;
//
//    public JwtAuthenticationFilter(UserPrincipalDetailService userPrincipalDetailService, JwtTokenProvider jwtTokenProvider, FrogMemberRepository frogMemberRepository) {
//        this.userPrincipalDetailService = userPrincipalDetailService;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.frogMemberRepository = frogMemberRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String jwt = getJwtFromRequest(request);
//
//            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
//                String username = jwtTokenProvider.getUserNameFromJwt(jwt);
//                UserPrincipal userDetails;
//
//                if(StringUtils.hasText(request.getHeader("Temp-Auth")))   {
//                    FrogMemberEntity frogMemberEntity = frogMemberRepository.findByUsernameAndActive(username, ActiveType.ACTIVE)
//                            .orElseThrow(() -> new FrogApiException(ResponseCode.DATA_NOT_FOUND, "해당하는 유저가 존재하지 않습니다.", HttpStatus.NOT_FOUND));
//                    userDetails = UserPrincipal.createTemp(frogMemberEntity);
//                }
//                else    {
//                    userDetails = userPrincipalDetailService.loadUserByUsername(username);
//                }
//
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception e) {
//            logger.error("Could not set user authentication in security context", e);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = StringUtils.hasText(request.getHeader("Authorization")) ?
//                request.getHeader("Authorization") : request.getHeader("Temp-Auth");
//
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}