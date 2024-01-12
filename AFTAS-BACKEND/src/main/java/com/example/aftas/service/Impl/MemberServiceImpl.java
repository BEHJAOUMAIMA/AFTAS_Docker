package com.example.aftas.service.Impl;

import com.example.aftas.domain.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member update(Member memberUpdated, Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member existingMember = member.get();
            existingMember.setName(memberUpdated.getName());
            existingMember.setFamilyName(memberUpdated.getFamilyName());
            existingMember.setAccessionDate(memberUpdated.getAccessionDate());
            existingMember.setNationality(memberUpdated.getNationality());
            existingMember.setNationality(memberUpdated.getNationality());
            existingMember.setIdentityDocumentType(memberUpdated.getIdentityDocumentType());
            existingMember.setIdentityDocumentType(memberUpdated.getIdentityDocumentType());
            existingMember.setIdentityNumber(memberUpdated.getIdentityNumber());
            return memberRepository.save(existingMember);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Member getById(Long id) {
        return memberRepository.getMemberById(id);
    }

    @Override
    public List<Member> searchMembers(Long id, String name, String familyName) {
        return memberRepository.searchMembers(id, name, familyName);
    }

    @Override
    public List<Member> getMembersByCompetition(Long competitionId) {
        return memberRepository.findMembersByCompetitionId(competitionId);
    }
}