package com.example.aftas.dto.response;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Level;
import com.example.aftas.dto.request.FishRequestDTO;
import jakarta.validation.constraints.Positive;

public record FishResponseDTO(
        Long id,
        String name,
        Double averageWeight,
        Level level
) {

    public static FishResponseDTO fromFish(Fish fish){
        return new FishResponseDTO(
                fish.getId(),
                fish.getName(),
                fish.getAverageWeight(),
                fish.getLevel()
        );
    }
}
