package com.amr.project.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "email", "username", "password"})
@EqualsAndHashCode(of = {"id", "email", "username"})
public class User implements UserDetails {
    //TODO надо продумать юзера, слишком много у него связей,
    // нужны-ли они, возможно где-то вместо связи с ентити использовать id,
    // иначе есть вероятность попасть в констрейнты и не отстроить нормальную структуру
    // для взаимодействия с БД
    // что-то ссылается на само себя в юзере()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String email;
    private String username;
    private String password;
    private boolean activate;
    private String activationCode;
    private boolean isUsingTwoFactorAuth;
    private String secret;
    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;


    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;


    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Image> images;


    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "user_coupons",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Coupon> coupons;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartItem> cart;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Order> orders;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id")
    private Set<Review> reviews;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "user_shop",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private Set<Shop> shops;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Discount> discounts;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Favorite favorite;

    @OneToMany
    @JoinColumn(name = "user_from_id")
    private Set<Message> messages;










    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
