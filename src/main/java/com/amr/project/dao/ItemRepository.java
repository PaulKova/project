package com.amr.project.dao;


import com.amr.project.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findFirst4ByOrderByRatingDesc();

    List<Item> findItemByNameContainingOrderByRatingDesc(String searchString, Pageable pageable);


}
