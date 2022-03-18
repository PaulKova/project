package com.amr.project.model.entity;

import com.amr.project.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table()
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "data", nullable = false)
    private Calendar orderDate;//дата заказа

    @Column(name = "delyv_data")
    private Calendar expectedDeliveryDate;//ожидаемая дата доставки

    @Column(name = "total")
    private BigDecimal grandTotal;

    @Column(name = "currency")
    private String currency;

    @Column(name = "descript")
    private String description;//комментарий к заказу со стороны user'a

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;//статус заказа

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itemsInOrder;


    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;


}
