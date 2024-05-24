package com.football.controllers;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.dtos.inDTO.CompetitionInDTO;
import com.football.dtos.outDTO.clubOutDTO.ClubOutDTO;
import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.services.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
