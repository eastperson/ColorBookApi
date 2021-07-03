package com.colorbook.api.common.repository;

import com.colorbook.api.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
