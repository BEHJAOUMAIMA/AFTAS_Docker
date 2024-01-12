package com.example.aftas.repository;

import com.example.aftas.domain.Competition;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByDate(LocalDate date);
    Competition getCompetitionByCode(String code);


}
