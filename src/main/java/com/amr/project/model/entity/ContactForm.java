package com.amr.project.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "text", nullable = false, unique = true)
    private String text;

    @Column(name = "date_time", nullable = false, unique = true)
    private LocalDateTime dateTime;

    @Column(name = "answer", unique = true)
    private String answer;

    @Column(name = "is_moderated", nullable = false)
    private boolean isModerated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ContactForm contactForm = (ContactForm) o;
        return id != null && Objects.equals(id, contactForm.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}