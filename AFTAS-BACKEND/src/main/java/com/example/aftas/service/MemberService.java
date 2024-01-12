package com.example.aftas.service;

import com.example.aftas.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService{

    Member save(Member member);
    List<Member> findAll();
    Optional<Member> findById(Long id);
    Member update(Member memberUpdated, Long id);
    void delete(Long id);
    Member getById(Long id);
    List<Member> searchMembers(Long id, String name, String familyName);
    List<Member> getMembersByCompetition(Long competitionId);

}
