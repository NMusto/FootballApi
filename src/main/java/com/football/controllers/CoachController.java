package com.football.controllers;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.entities.Coach;
import com.football.services.CoachService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coach")
public class CoachController {

    private CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("/create")
    public ResponseEntity<Coach> createCoach(@RequestBody @Valid CoachInDTO coachInDTO) {
        return new ResponseEntity<>(coachService.createCoach(coachInDTO), HttpStatus.CREATED);
    }
}
