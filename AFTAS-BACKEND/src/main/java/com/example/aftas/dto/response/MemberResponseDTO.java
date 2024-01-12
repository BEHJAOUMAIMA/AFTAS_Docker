package com.example.aftas.dto.response;

import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.dto.request.MemberRequestDTO;
import com.example.aftas.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public record MemberResponseDTO(
        Long id,
        String name,
        String familyName,
        LocalDateTime accessionDate,
        String nationality,
        IdentityDocumentType identityDocumentType,
        String identityNumber,
        @JsonIgnore
        List<Ranking> rankings

        ) {

        public static MemberResponseDTO fromMember(Member member) {
                return new MemberResponseDTO(
                        member.getId(),
                        member.getName(),
                        member.getFamilyName(),
                        member.getAccessionDate(),
                        member.getNationality(),
                        member.getIdentityDocumentType(),
                        member.getIdentityNumber(),
                        member.getRankings()
                );
        }

}
