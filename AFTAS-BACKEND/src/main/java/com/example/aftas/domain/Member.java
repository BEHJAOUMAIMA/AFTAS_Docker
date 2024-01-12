package com.example.aftas.domain;

import com.example.aftas.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String familyName;

    private LocalDateTime accessionDate;

    private String nationality;

    private IdentityDocumentType identityDocumentType;

    private String identityNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Hunting> huntings;

}
