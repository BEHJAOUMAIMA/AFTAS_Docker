package com.example.aftas.repository;

import com.example.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member getMemberById(Long id);

    @Query("SELECT m FROM Member m " +
            "WHERE (:id IS NULL OR m.id = :id) " +
            "OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(m.familyName) LIKE LOWER(CONCAT('%', :familyName, '%'))")
    List<Member> searchMembers(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("familyName") String familyName);

    @Query("SELECT m FROM Member m JOIN m.rankings r WHERE r.competition.id = :competitionId")
    List<Member> findMembersByCompetitionId(@Param("competitionId") Long competitionId);

}
