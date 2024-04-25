package com.football.controllers;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.dtos.outDTO.ClubOutDTO;
import com.football.entities.Club;
import com.football.services.ClubService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClubController {

    private ClubService clubService;

    private ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/createclub")
    public ResponseEntity<Club> createClub(@RequestBody @Valid ClubInDTO clubInDTO) {
        return new ResponseEntity<>(clubService.createClub(clubInDTO), HttpStatus.CREATED);
    }
}
