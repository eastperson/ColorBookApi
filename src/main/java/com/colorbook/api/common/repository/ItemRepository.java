package com.colorbook.api.common.repository;

import com.colorbook.api.common.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
