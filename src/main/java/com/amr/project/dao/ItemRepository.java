package com.amr.project.dao;


import com.amr.project.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findFirst4ByOrderByRatingAsc();
}
