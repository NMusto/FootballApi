package com.football.controllers;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.dtos.outDTO.coachOutDTO.CoachOutDTO;
import com.football.services.CoachService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {

    private CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("/create")
    public ResponseEntity<CoachOutDTO> createCoach(@RequestBody @Valid CoachInDTO coachInDTO) {
        return new ResponseEntity<>(coachService.createCoach(coachInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findcoach/{coachId}")
    public ResponseEntity<CoachOutDTO> findCoachById(@PathVariable @Valid Long coachId) {
        return new ResponseEntity<>(coachService.findCoachById(coachId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CoachOutDTO>> findAllCoaches() {
        return new ResponseEntity<>(coachService.findAllCoaches(), HttpStatus.OK);
    }

    @PutMapping("/update/{coachId}")
    public ResponseEntity<CoachOutDTO> updateCoach(@PathVariable @Valid Long coachId, @RequestBody @Valid CoachInDTO coachInDTO) {
        return new ResponseEntity<>(coachService.updateCoachById(coachId, coachInDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{coachId}")
    public ResponseEntity<String> deleteCoachById(@PathVariable @Valid Long coachId) {
        return new ResponseEntity<>(coachService.deleteCoachById(coachId), HttpStatus.OK);
    }
}
