package com.amr.project.model.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDto {
    private Long id;
    private byte[] picture;
    private Boolean isMain;

    private ShopDto shop;


}
