package com.amr.project.dao;


import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findFirst4ByOrderByRatingDesc();

    //TODO протестировать, что запрос работает корректно (поиск по ключевому слову в имени вне зависимости от количества символов до и после
    @Query(value = "SELECT * FROM item WHERE name like '%':searchstring'%' ORDER BY name DESC", nativeQuery = true)
    List<Item> selectItems(@Param("searchString")String searchString);
}
