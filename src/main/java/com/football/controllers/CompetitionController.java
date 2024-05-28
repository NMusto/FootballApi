package com.football.controllers;

import com.football.dtos.inDTO.CompetitionInDTO;
import com.football.dtos.outDTO.competitionOutDTO.CompetitionClubsOutDTO;
import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.services.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompetitionOutDTO> createClub(@RequestBody @Valid CompetitionInDTO competitionInDTO) {
        return new ResponseEntity<>(competitionService.createCompetition(competitionInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findcompetition/{competitionId}")
    public ResponseEntity<CompetitionOutDTO> findCompetitionById(@PathVariable @Valid Long competitionId) {
        return new ResponseEntity<>(competitionService.findCompetitionById(competitionId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CompetitionOutDTO>> findAllCompetitions() {
        return new ResponseEntity<>(competitionService.findAllCompetitions(), HttpStatus.OK);
    }

    @PutMapping("/update/{competitionId}")
    public ResponseEntity<CompetitionOutDTO> updateCompetitionById(@PathVariable @Valid Long competitionId, @RequestBody @Valid CompetitionInDTO competitionInDTO) {
        return new ResponseEntity<>(competitionService.updateCompetitionById(competitionId, competitionInDTO), HttpStatus.OK);
    }

    @PutMapping("/addclub/{competitionId}/{clubId}")
    public ResponseEntity<String> addClub(@PathVariable @Valid Long competitionId, @PathVariable @Valid Long clubId) {
        return new ResponseEntity<>(competitionService.addClub(competitionId, clubId), HttpStatus.CREATED);
    }

    @GetMapping("/findclubs/{competitionId}")
    public ResponseEntity<CompetitionClubsOutDTO> findClubsByCompetitionId(@PathVariable @Valid Long competitionId) {
        return new ResponseEntity<>(competitionService.findClubsByCompetitionId(competitionId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteclub/{competitionId}/{clubId}")
    public ResponseEntity<String> deleteClubFromCompetition(@PathVariable @Valid Long competitionId, @PathVariable @Valid Long clubId) {
        return new ResponseEntity<>(competitionService.deleteClubFromCompetition(competitionId, clubId), HttpStatus.OK);
    }

}
