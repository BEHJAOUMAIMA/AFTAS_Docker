package com.example.aftas.dto.response;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record HuntingResponseDTO(
        Long id,
        @JsonIgnore
        Fish fish,
        @JsonIgnore
        Member member,
        @JsonIgnore
        Competition competition
) {

    public static HuntingResponseDTO fromHunting(Hunting hunting){
        return new HuntingResponseDTO(
                hunting.getId(),
                hunting.getFish(),
                hunting.getMember(),
                hunting.getCompetition()
        );
    }
}
