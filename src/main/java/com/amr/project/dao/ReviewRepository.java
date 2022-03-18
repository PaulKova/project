package com.amr.project.dao;

import com.amr.project.model.entity.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Table()
public interface ReviewRepository extends JpaRepository<Review, Long> {

    //запрос на поиск всех записей с полем isModerated=false
    @Query("select r from Review r where r.isModerated = false")
    List<Review> findByIsModeratedIsFalse();


    //запрос на поиск всех записей с полем isModerated=false и
    //отсортированные по возрастанию поля date(начиная от самых "старых")
    @Query("select r from Review r where r.isModerated = false order by r.date")
    List<Review> findByIsModeratedIsFalseOrderByDateAsc(Sort sort);

    //запрос на поиск всех записей по определенному полю user.id с условием:
    //isModerated=true
    //isModerateAccept=true
    @Query("select r from Review r " +
            "where r.user.id = ?1 and r.isModerated = true and r.isModerateAccept = true " +
            "order by r.date")
    List<Review> findByUser_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(Long id);

    //запрос на поиск всех записей по определенному полю shop.id с условием:
    //isModerated=true
    //isModerateAccept=true
    @Query("select r from Review r " +
            "where r.shop.id = ?1 and r.isModerated = true and r.isModerateAccept = true " +
            "order by r.date")
    List<Review> findByShop_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(Long id);

    //запрос на поиск всех записей по определенному полю item.id с условием:
    //isModerated=true
    //isModerateAccept=true
    @Query("select r from Review r " +
            "where r.item.id = ?1 and r.isModerated = true and r.isModerateAccept = true " +
            "order by r.date")
    List<Review> findByItem_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(Long id);

    //пример запроса на поиск всех записей, содержащие в поле "text" вхождение набора символов "string"
    @Query("select r from Review r where r.text like concat('%', :string, '%')")
    List<Review> findByTextContaining(@Param("string") String string);

}
