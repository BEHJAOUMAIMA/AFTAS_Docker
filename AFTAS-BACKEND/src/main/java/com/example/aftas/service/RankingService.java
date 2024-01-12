package com.example.aftas.service;



import com.example.aftas.domain.RankId;
import com.example.aftas.domain.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RankingService {

    Ranking save(Ranking ranking);
    List<Ranking> findAll();
    Optional<Ranking> findById(RankId id);
    List<Ranking> findByMember(Long member);
    List<Ranking> findByCompetition(String competition);
    Ranking findByMemberAndCompetition(Long member, String competition);
    List<Ranking> sortParticipantsByScore(Long competitionId);
    Ranking update(Ranking rankingUpdated);
    void delete(Ranking ranking);
    List<Ranking> getPodium(Long competitionId);

}
