package com.colorbook.api.common.repository;

import com.colorbook.api.common.entity.ColorBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorBookRepository extends JpaRepository<ColorBook,Long> {
}
