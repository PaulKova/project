package com.amr.project.dao;


import com.amr.project.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findFirst4ByOrdOrderByRatingDesc();


    @Query("select s from Shop s where s.isModerated = true")
    List<Shop> findShopByNoModerated();


    @Query("select s from Shop s where s.isModerateAccept = true")
    List<Shop> findExistsShops();

    //TODO протестировать, что запрос работает корректно (поиск по ключевому слову в имени вне зависимости от количества символов до и после
    @Query(value = "SELECT * FROM shop WHERE name LIKE '% :searchString %' ORDER BY name DESC", nativeQuery = true)
    List<Shop> selectShops(@Param("searchString")String searchString);
}
