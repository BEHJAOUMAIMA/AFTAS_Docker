package com.example.aftas.dto.request;

import com.example.aftas.domain.Member;
import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;


public record MemberRequestDTO(
        @NotBlank
        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,

        @NotBlank
        @NotNull(message = "Family name cannot be null")
        @Size(min = 1, max = 50, message = "Family name must be between 1 and 50 characters")
        String familyName,

        @NotNull(message = "Accession date cannot be null")
        @FutureOrPresent(message = "Accession date must be in the present or the future")
        LocalDateTime accessionDate,

        @NotNull(message = "Nationality cannot be null")
        @Size(min = 1, max = 50, message = "Nationality must be between 1 and 50 characters")
        String nationality,

        @NotNull(message = "Identity document type cannot be null")
        @Enumerated(EnumType.STRING)
        IdentityDocumentType identityDocumentType,

        @NotNull(message = "Identity number cannot be null")
        @Size(min = 1, max = 20, message = "Identity number must be between 1 and 20 characters")
        String identityNumber

) {

        public Member toMember() {
                return Member.builder()
                        .name(this.name)
                        .familyName(this.familyName)
                        .accessionDate(this.accessionDate)
                        .nationality(this.nationality)
                        .identityDocumentType(this.identityDocumentType)
                        .identityNumber(this.identityNumber)
                        .build();
        }
}
