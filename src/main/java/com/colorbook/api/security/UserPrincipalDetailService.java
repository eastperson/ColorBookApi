//package com.colorbook.api.security;
//
//import com.froghinter.frog.api.exception.FrogApiException;
//import com.froghinter.frog.common.constants.ResponseCode;
//import com.froghinter.frog.common.entity.FrogMemberEntity;
//import com.froghinter.frog.common.entity.enums.ActiveType;
//import com.froghinter.frog.common.repository.FrogMemberRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.time.ZonedDateTime;
//
//@Service
//public class UserPrincipalDetailService implements UserDetailsService {
//
//    private final FrogMemberRepository frogMemberRepository;
//
//
//    public UserPrincipalDetailService(
//            FrogMemberRepository frogAdminMemberRepository
//    ) {
//        this.frogMemberRepository = frogAdminMemberRepository;
//    }
//
//    @Override
//    public UserPrincipal loadUserByUsername(String username) throws FrogApiException {
//
//        FrogMemberEntity frogMemberEntity = frogMemberRepository.findByUsernameAndActive(username, ActiveType.ACTIVE)
//                .orElseThrow(
//                        () -> new FrogApiException(
//                                ResponseCode.UNAUTHORIZED,
//                                "해당하는 유저가 존재하지 않습니다.",
//                                HttpStatus.UNAUTHORIZED
//                        )
//                );
//        // 마지막 로그인 시간 기록
//        frogMemberEntity.setLastSigninAt(ZonedDateTime.now());
//        frogMemberRepository.save(frogMemberEntity);
//
//        return UserPrincipal.create(frogMemberEntity);
//    }
//}
