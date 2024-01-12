package com.example.aftas.service.Impl;

import com.example.aftas.domain.*;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final FishService fishService;
    private final CompetitionService competitionService;
    private final MemberService memberService;
    private final RankingService rankingService;

    @Override
    public Hunting save(Hunting hunting, Double weight) {
        Fish fish = fishService.getByName(hunting.getFish().getName());
        if (fish == null) {
            throw new EntityNotFoundException("Fish not found with name: " + hunting.getFish().getName());
        }

        Competition competition = competitionService.findById(hunting.getCompetition().getId()).orElse(null);
        if (competition == null) {
            throw new EntityNotFoundException("Competition not found with id: " + hunting.getCompetition().getId());
        }

        Member member = memberService.findById(hunting.getMember().getId()).orElse(null);
        if (member == null) {
            throw new EntityNotFoundException("Member not found with id: " + hunting.getMember().getId());
        }

        hunting.setFish(fish);
        hunting.setCompetition(competition);
        hunting.setMember(member);

        Ranking ranking = rankingService.findByMemberAndCompetition(member.getId(), competition.getCode());
        if (weight >= fish.getAverageWeight() && ranking != null) {
            Hunting existingHunt = checkIfFishAlreadyHunted(member, competition, fish);
            ranking.setScore(ranking.getScore() + fish.getLevel().getPoints());
            rankingService.update(ranking);

            if (existingHunt == null) {
                hunting.setNumberOfFish(1);
                return huntingRepository.save(hunting);
            }

            existingHunt.setNumberOfFish(existingHunt.getNumberOfFish() + 1);
            return huntingRepository.save(existingHunt);
        } else {
            throw new ValidationException("Weight is less than fish's average weight or ranking not found");
        }
    }

    @Override
    public List<Hunting> getAll() {
        return huntingRepository.findAll();
    }

    @Override
    public Hunting getById(Long id) {
        return huntingRepository.getHuntingsById(id);
    }

    @Override
    public List<Hunting> getByCompetition(String codeCompetition) {
        return huntingRepository.getHuntingsByCompetition(competitionService.getByCode(codeCompetition));
    }

    @Override
    public List<Hunting> getByMember(Long memberId) {
        return huntingRepository.getHuntingsByMember(memberService.getById(memberId));
    }


    @Override
    public List<Hunting> getByCompetitionAndMember(String codeCompetition, Long memberId) {
        return huntingRepository.getHuntingsByCompetitionAndMember(competitionService.getByCode(codeCompetition), memberService.getById(memberId));
    }

    @Override
    public Hunting update(Hunting hunting, Long id) {
        Hunting existingHunting = getById(id);
        if (existingHunting != null){
            existingHunting.setNumberOfFish(hunting.getNumberOfFish());
            return huntingRepository.save(existingHunting);
        }
        return null;    }

    @Override
    public Hunting checkIfFishAlreadyHunted(Member member, Competition competition, Fish fish) {
        List<Hunting> allHunts = getAll();
        if (allHunts.isEmpty()) {
            return null;
        }
        List<Hunting> hunts = allHunts.stream()
                .filter(hunting -> hunting.getMember()
                        .equals(member) && hunting.getCompetition()
                        .equals(competition) && hunting.getFish()
                        .equals(fish)).toList();
        return hunts.isEmpty() ? null : hunts.get(0);    }

    @Override
    public void delete(Long id) {
        Hunting hunting = getById(id);
        if (hunting != null){
            huntingRepository.delete(hunting);
        }
    }
}
