package com.amr.project.model.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;

    private List<ItemDto> items;

}
