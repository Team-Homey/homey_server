package com.homey.homeyserver.domain;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreatedDate
    private LocalDateTime regDate;
    private String name;
    private String hashCode;
    @ColumnDefault("0")
    private Integer point;
    @OneToMany(mappedBy = "family")
    private List<User> users;
    @OneToMany(mappedBy = "family")
    private List<RecommendedContent> recommendedContents;
    @OneToMany(mappedBy = "family")
    private List<Question> questions;
}
