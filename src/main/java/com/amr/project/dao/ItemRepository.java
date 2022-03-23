package com.amr.project.dao;


import com.amr.project.model.entity.Item;
import com.amr.project.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Calendar;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findFirst4ByOrderByRatingDesc();

    List<Item> findItemByNameContainingOrderByRatingDesc(String searchString, Pageable pageable);

    @Query("select i from Item i where i.shop.id = ?1 order by i.name")
    List<Item> findByShop_IdIsOrderByNameAsc(Long id);

    @Query("select i from Item i " +
            "where i.shop.id = :id and upper(i.name) like upper(:name) and i.order.status = :status or i.order.status = :status1 or i.order.status = :status2 and i.order.orderDate between :orderDateStart and :orderDateEnd " +
            "order by i.order.orderDate DESC")
    List<Item> findByShop_IdIsAndNameLikeIgnoreCaseAndOrder_StatusIsOrOrder_StatusIsOrOrder_StatusIsAndOrder_OrderDateIsBetweenOrderByOrder_OrderDateDesc(@Param("id") Long id, @Param("name") String name, @Param("status") Status status, @Param("status1") Status status1, @Param("status2") Status status2, @Param("orderDateStart") Calendar orderDateStart, @Param("orderDateEnd") Calendar orderDateEnd);






}
