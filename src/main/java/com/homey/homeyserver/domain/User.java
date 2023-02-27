package com.homey.homeyserver.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate birth;
    private LocalDateTime regDate;
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
