package com.colorbook.api.common.entity;

import com.colorbook.api.common.model.Gender;
import com.colorbook.api.common.model.SnsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.List;

@Entity @Getter
@Setter
@Builder @ToString(exclude = "colorBooks")
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ColorBook> colorBooks;

    private String email;

    private SnsType snsType;

    private String nickname;

    private String realName;

    private String birthday;

    private String phoneNumber;

    private Gender gender;



}
