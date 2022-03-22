package com.amr.project.model.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private Long id;
    private byte[] picture;
    private Boolean isMain;

    @JsonManagedReference
    private ShopDto shop;



}
