package com.example.aftas.dto.request;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;

public record AddMemberRequestDTO(
        Long competitionId,
        Long memberId
) {
    public Ranking toRanking() {
        return Ranking.builder()
                .competition(Competition.builder().id(competitionId).build())
                .member(Member.builder().id(memberId).build())
                .build();
    }
}
