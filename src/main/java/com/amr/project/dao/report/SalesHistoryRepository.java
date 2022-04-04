package com.amr.project.dao.report;

import com.amr.project.model.entity.report.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;
import java.util.List;

public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Long> {


    @Query("select s from SalesHistory s LEFT JOIN FETCH s.item where s.item.id = ?1 and s.orderDate between ?2 and ?3 order by s.orderDate")
    List<SalesHistory> findByItem_IdIsAndOrderDateBetweenOrderByOrderDateAsc(Long id, Calendar orderDateStart, Calendar orderDateEnd);



}