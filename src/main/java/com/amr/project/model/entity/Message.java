package com.amr.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import org.springframework.data.relational.core.mapping.Table;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column
    private String textMessage;

    @Column
    private boolean viewed;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

}
