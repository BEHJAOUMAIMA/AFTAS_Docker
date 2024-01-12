package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {

    private Integer rank;

    private Integer score;

    @EmbeddedId
    private RankId id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    @JsonIgnoreProperties("rankings")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionId")
    @JsonIgnoreProperties("rankings")
    private Competition competition;

}
