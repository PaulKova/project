package com.amr.project.model.dto;

import ch.qos.logback.core.status.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Long.class)
public class OrderDto {

    private Long id;
    private String qiwiId;
    private Calendar orderDate;
    private Calendar expectedDeliveryDate;
    private BigDecimal grandTotal;
    private String currency;
    private String description;


    //@JsonManagedReference
    private StatusDto status;
    //@JsonManagedReference
    private UserDto user;
    //@JsonBackReference
    private List<ItemDto> itemsInOrder;
    //@JsonManagedReference
    private AddressDto address;


}
