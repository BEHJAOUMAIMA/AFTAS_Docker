package com.example.aftas.repository;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    Hunting getHuntingsById(Long id);

    List<Hunting> getHuntingsByCompetition(Competition competition);

    List<Hunting> getHuntingsByMember(Member member);

    List<Hunting> getHuntingsByCompetitionAndMember(Competition competition, Member member);
}
