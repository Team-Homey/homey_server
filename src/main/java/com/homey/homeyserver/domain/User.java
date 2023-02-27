package com.homey.homeyserver.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private Integer age;
    private String gender;
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate birth;
    private LocalDateTime regDate;
    @Column
    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer address;
    @ManyToOne
    private Family family;
    @OneToMany(mappedBy = "user")
    private List<Photo> photos;
    @OneToMany(mappedBy = "user")
    private List<Emotion> emotions;
    private String picture;


    //해당 User 객체의 Authentication 객체를 반환한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
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
