package com.example.aftas.repository;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.RankId;
import com.example.aftas.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {
    List<Ranking> findByCompetitionId(Long competitionId);

    List<Ranking> getRankingByMember(Member member);

    List<Ranking> getRankingByCompetition(Competition competition);

    Ranking getRankingByMemberAndCompetition(Optional<Member> member, Competition competition);

    @Query("SELECT MAX(r.rank) FROM Ranking r WHERE r.id.competitionId = :competitionId")
    Integer findMaxRankByCompetitionId(@Param("competitionId") Long competitionId);

}
