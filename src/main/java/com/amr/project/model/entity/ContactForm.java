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
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "answer")
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