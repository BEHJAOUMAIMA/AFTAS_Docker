package com.example.aftas.service;


import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HuntingService {
    Hunting save(Hunting hunting, Double weight);
    List<Hunting> getAll();
    Hunting getById(Long id);
    List<Hunting> getByCompetition(String codeCompetition);
    List<Hunting> getByMember(Long memberId);
    List<Hunting> getByCompetitionAndMember(String codeCompetition, Long memberId);
    Hunting update(Hunting hunting, Long id);
    Hunting checkIfFishAlreadyHunted(Member member, Competition competition, Fish fish);
    void delete(Long id);


}
