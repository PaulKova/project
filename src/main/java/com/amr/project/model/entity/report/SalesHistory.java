package com.amr.project.model.entity.report;

import com.amr.project.model.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Calendar orderDate;     //Дата продажи товара
    private int count;              //Кол-во проданного товара в этот день
    private BigDecimal basePrice;   //Себестоимость товара ("= basePrice" в таблице "item")
    private BigDecimal price;       //Цена проданного товара в этот день ("= price" в таблице "item)


    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;              //Ссылка на проданную позицию товара

}
