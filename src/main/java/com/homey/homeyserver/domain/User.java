package com.homey.homeyserver.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private Integer age;
    private String gender;
    private LocalDate birth;
    @Column
    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;
    private Integer address;
    @ManyToOne
    private Family family;
    @OneToMany(mappedBy = "user")
    private List<Photo> photos;
    @OneToMany(mappedBy = "user")
    private List<Emotion> emotions;
    private String picture;

}
