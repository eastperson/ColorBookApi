package com.colorbook.api.common.repository;

import com.colorbook.api.common.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Long> {
}
