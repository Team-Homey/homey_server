package com.homey.homeyserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime regDate;
    private String name;
    private String hashcode;


    @OneToMany(mappedBy = "family")
    private List<User> users;
    @OneToMany(mappedBy = "family")
    private List<RecommendedContent> recommendedContents;
    @OneToMany(mappedBy = "family")
    private List<Question> questions;

}
