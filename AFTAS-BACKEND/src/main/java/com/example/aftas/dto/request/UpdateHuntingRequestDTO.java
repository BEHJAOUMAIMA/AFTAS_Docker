package com.example.aftas.dto.request;

import com.example.aftas.domain.Hunting;

public record UpdateHuntingRequestDTO(
        Integer numberOfFish

) {
    public Hunting toHunting() {
        return Hunting.builder()
                .NumberOfFish(numberOfFish)
                .build();
    }
}
