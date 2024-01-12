package com.example.aftas.dto.request;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HuntingRequestDTO(
        String fish,
        Long member,
        Long competition,
        @Positive
        @NotNull
        Double weight

) {
    public Hunting toHunting() {
        return Hunting.builder()
                .member(Member.builder().id(member).build())
                .competition(Competition.builder().id(competition).build())  // Set the competition ID
                .fish(Fish.builder().name(fish).build())
                .build();
    }
}
