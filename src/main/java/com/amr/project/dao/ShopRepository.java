package com.amr.project.dao;


import com.amr.project.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findFirst4ByOrdOrderByRatingDesc();


    @Query("select s from Shop s where s.isModerated = true")
    List<Shop> findShopByNoModerated();


    @Query("select s from Shop s where s.isModerateAccept = true")
    List<Shop> findExistsShops();

}
