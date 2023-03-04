package com.homey.homeyserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDate date;

    @ManyToOne
    private Family family;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}
