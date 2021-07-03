package com.colorbook.api.web.rest;

import com.colorbook.api.common.entity.Member;
import com.colorbook.api.common.model.Gender;
import com.colorbook.api.common.model.SnsType;
import com.colorbook.api.common.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestConroller {

    private final MemberRepository memberRepository;

    @GetMapping("")
    public String test(){
        return "test";
    }

    @PostConstruct
    public void init(){
        Member member = memberRepository.save(
                Member.builder()
                        .email("kjuioqqq@naver.com")
                        .nickname("ep")
                        .createdAt(ZonedDateTime.now())
                        .updatedAt(ZonedDateTime.now())
                        .birthday("19931211")
                        .gender(Gender.MALE)
                        .snsType(SnsType.EMAIL)
                        .realName("김동인")
                        .phoneNumber("01027375157")
                        .build()
        );
        System.out.println(member);
    }

}
