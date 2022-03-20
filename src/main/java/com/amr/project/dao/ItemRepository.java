package com.amr.project.dao;


import com.amr.project.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findFirst4ByOrderByRatingDesc();

    List<Item> searchItemsByNameSortedByRatingDesc (@Param("searchString")String searchString, Pageable pageable);


}
