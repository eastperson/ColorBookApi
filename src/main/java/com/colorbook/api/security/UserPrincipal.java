//package com.colorbook.api.security;
//
//import com.colorbook.api.common.entity.ColorBookMember;
//import lombok.Builder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Builder
//public class UserPrincipal implements UserDetails {
//
//    private Long userId;
//
//    private String username;
//
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public static UserPrincipal create(ColorBookMember user) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        // 판매자 권한이 있을 경우
//        if (user.getSellerStatus().equals(SellerStatus.DONE)) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
//        }
//
//        String password = user.getSnsType().equals(SnsType.EMAIL) ? user.getPassword() : passwordEncoder().encode(user.getUsername());
//
//        return UserPrincipal.builder()
//                .userId(user.getId())
//                .username(user.getUsername())
//                .authorities(authorities)
//                .password(password)
//                .build();
//    }
//
//    public static UserPrincipal createTemp(FrogMemberEntity frogMemberEntity) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
//        return UserPrincipal.builder()
//                .userId(frogMemberEntity.getId())
//                .username(frogMemberEntity.getUsername())
//                .authorities(authorities)
//                .password(frogMemberEntity.getPassword())
//                .build();
//    }
//
//    public static PasswordEncoder passwordEncoder() {
//        SecureRandom random = new SecureRandom();
//        byte[] bytes = new byte[16];
//        random.nextBytes(bytes);
//        return new BCryptPasswordEncoder();
//    }
//
//    public Long getUserId(){
//        return this.userId;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
