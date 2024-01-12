package com.example.aftas.service.Impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HuntingServiceImplTest {
    private FishService fishService;
    private CompetitionService competitionService;
    private MemberService memberService;
    private RankingService rankingService;
    private HuntingService huntingService;
    @BeforeEach
    void setUp() {
        HuntingRepository huntingRepository = mock(HuntingRepository.class);
        fishService = mock(FishService.class);
        competitionService = mock(CompetitionService.class);
        memberService = mock(MemberService.class);
        rankingService = mock(RankingService.class);
        huntingService = new HuntingServiceImpl(huntingRepository, fishService, competitionService, memberService, rankingService);
    }



    @Test
    void testSaveWithInvalidWeightOrNoRanking() {
        Fish fish = new Fish();
        fish.setAverageWeight(10.0);
        Competition competition = new Competition();
        competition.setId(1L);
        Member member = new Member();
        member.setId(1L);

        Hunting hunting = new Hunting();
        hunting.setFish(fish);
        hunting.setCompetition(competition);
        hunting.setMember(member);

        when(fishService.getByName(anyString())).thenReturn(fish);
        when(competitionService.findById(anyLong())).thenReturn(java.util.Optional.of(competition));
        when(memberService.findById(anyLong())).thenReturn(java.util.Optional.of(member));
        when(rankingService.findByMemberAndCompetition(anyLong(), anyString())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> huntingService.save(hunting, 8.0));
    }

}