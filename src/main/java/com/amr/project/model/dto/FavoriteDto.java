package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    private Long id;

    @JsonBackReference
    private List<ShopDto> shops;
    @JsonBackReference
    private List<ItemDto> items;
    @JsonManagedReference
    private UserDto user;
}