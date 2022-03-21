package com.amr.project.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
// ToDo Везде, где связь BiDerectional с 1 стороны должен стоять JsonIgnore / JsonBackReference
    private Long id;
    private String name;

    @JsonBackReference
    private List<ItemDto> items;

}
