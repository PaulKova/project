package com.amr.project.model.entity;

import com.amr.project.model.enums.Status;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Table(name="order_by_user")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "data")
    private LocalDateTime orderDate;//дата заказа

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
    @ToString.Exclude
    private User user;

    @Column(name = "qiwi_Id")
    private String qiwiId;

    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = @JoinColumn(name = "order_by_user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @ToString.Exclude
    private List<Item> itemsInOrder;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
