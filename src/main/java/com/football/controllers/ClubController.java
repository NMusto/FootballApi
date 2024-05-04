package com.football.controllers;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.entities.Club;
import com.football.projections.IClubOutProjection;
import com.football.services.ClubService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;

    private ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/create")
    public ResponseEntity<Club> createClub(@RequestBody @Valid ClubInDTO clubInDTO) {
        return new ResponseEntity<>(clubService.createClub(clubInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findclub/{clubId}")
    public ResponseEntity<IClubOutProjection> findClubById(@PathVariable @Valid Long clubId) {
        return new ResponseEntity<>(clubService.findClubById(clubId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<Page<IClubOutProjection>> findAllClubs(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "name") String name) {
        Page<IClubOutProjection> clubs = clubService.findAllClubs(PageRequest.of(page, size, Sort.by(name).ascending()));
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }
}
