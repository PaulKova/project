package com.amr.project.dao;


import com.amr.project.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findFirst4ByOrderByRatingDesc();


    @Query("select s from Shop s where s.isModerated = true")
    List<Shop> findShopByNoModerated();


    @Query("select s from Shop s where s.isModerateAccept = true")
    List<Shop> findExistsShops();

    //TODO протестировать, что запрос работает корректно (поиск по ключевому слову в имени вне зависимости от количества символов до и после
    @Query("select s from Shop s where s.name like concat('%', :searchString, '%') order by s.rating desc")
    List<Shop> searchShopsByNameSortedByRatingDesc (@Param("searchString")String searchString);
}
