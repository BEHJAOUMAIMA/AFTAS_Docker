package com.example.aftas.web.rest;


import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Ranking;
import com.example.aftas.dto.request.AddMemberRequestDTO;
import com.example.aftas.dto.request.CompetitionRequestDTO;
import com.example.aftas.dto.response.CompetitionResponseDTO;
import com.example.aftas.dto.response.RankingResponseDTO;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionRest {

    private final CompetitionService competitionService;

    public CompetitionRest(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> getAllCompetitions() {
        List<Competition> competitions = competitionService.findAll();
        List<CompetitionResponseDTO> competitionResponseDTOS = competitions.stream()
                .map(CompetitionResponseDTO::fromCompetition)
                .toList();

        return ResponseEntity.ok(competitionResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompetitionById(@PathVariable Long id) {
        Optional<Competition> competition = competitionService.findById(id);

        if (competition.isEmpty()) {
            return ResponseMessage.notFound("Competition not found with ID: " + id);
        }

        CompetitionResponseDTO competitionResponseDTO = CompetitionResponseDTO.fromCompetition(competition.get());
        return ResponseEntity.ok(competitionResponseDTO);
    }


    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> addCompetition(@Valid @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        Competition competition = competitionService.save(competitionRequestDTO.toCompetition());
        if(competition == null) {
            return ResponseMessage.badRequest("Competition not created");
        }else {
            return ResponseMessage.created("Competition created successfully", competition);
        }
    }

    @PutMapping("/update/{competitionId}")
    public ResponseEntity<ResponseMessage> updateCompetition(@PathVariable Long competitionId, @Valid @RequestBody CompetitionRequestDTO updatedCompetitionRequestDTO) {

        Competition updatedCompetition = updatedCompetitionRequestDTO.toCompetition();
        Competition competition = competitionService.update(updatedCompetition, competitionId);

        return ResponseEntity.ok(ResponseMessage.created("Competition updated successfully", competition).getBody());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable Long id) {
        Optional<Competition> existingCompetition = competitionService.findById(id);

        if (existingCompetition.isEmpty()) {
            return ResponseMessage.notFound("Competition not found with ID: " + id);
        }

        competitionService.delete(id);

        return ResponseMessage.ok("Competition deleted successfully with ID: " + id, null);
    }




}
