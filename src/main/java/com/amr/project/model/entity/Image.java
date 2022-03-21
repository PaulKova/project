package com.amr.project.model.entity;


import lombok.*;

import javax.persistence.*;
import org.springframework.data.relational.core.mapping.Table;

//Картинки будем хранить в БД (для удобства, хотя это и плохая практика)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private byte[] picture;

    private Boolean isMain;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;


}
