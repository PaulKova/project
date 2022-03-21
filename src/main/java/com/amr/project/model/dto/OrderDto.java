package com.amr.project.model.dto;

import ch.qos.logback.core.status.Status;
import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Calendar orderDate;
    private Calendar expectedDeliveryDate;
    private BigDecimal grandTotal;
    private String currency;
    private String description;


    private StatusDto status;
    private UserDto user;
    private List<ItemDto> itemsInOrder;
    private AddressDto address;


}
