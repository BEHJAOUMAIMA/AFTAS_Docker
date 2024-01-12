package com.example.aftas.dto.response;

import com.example.aftas.domain.Ranking;

public record RankingResponseDTO(
        Long memberId,
        String name,
        String familyName,
        Long competitionId,
        Integer score,
        Integer rank
) {
    public static RankingResponseDTO fromRanking(Ranking ranking) {
        return new RankingResponseDTO(
                ranking.getMember().getId(),
                ranking.getMember().getName(),
                ranking.getMember().getFamilyName(),
                ranking.getCompetition().getId(),
                ranking.getRank(),
                ranking.getScore()
        );
    }
}