package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Ranking;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface CompetitionService {
    Competition save(Competition competition);
    Page<Competition> findAll(Pageable pageable, Size size);
    Optional<Competition> findById(Long id);
    Competition update(Competition competitionUpdated, Long id);
    void delete(Long id);
    String generateCode(String location, LocalDate date);
    Competition getByCode(String code);
}
