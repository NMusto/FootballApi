package com.football.controllers;

import com.football.dtos.inDTO.PlayerInDTO;
import com.football.dtos.outDTO.playerOutDTO.PlayerOutDTO;
import com.football.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<PlayerOutDTO> createClub(@RequestBody @Valid PlayerInDTO playerInDTO) {
        return new ResponseEntity<>(playerService.createPlayer(playerInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findplayer/{playerId}")
    public ResponseEntity<PlayerOutDTO> findClubById(@PathVariable @Valid Long playerId) {
        return new ResponseEntity<>(playerService.findPlayerById(playerId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<Page<PlayerOutDTO>> findAllPlayers(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "name") String name) {
        Page<PlayerOutDTO> clubs = playerService.findAllPlayers(PageRequest.of(page, size, Sort.by(name).ascending()));
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @PutMapping("/update/{playerId}")
    public ResponseEntity<PlayerOutDTO> updatePlayerById(@PathVariable @Valid Long playerId, @RequestBody @Valid PlayerInDTO playerInDTO) {
        return new ResponseEntity<>(playerService.updatePlayerById(playerId, playerInDTO), HttpStatus.OK);
    }

    @PutMapping("/addclub/{playerId}")
    public ResponseEntity<String> addClub (@PathVariable @Valid Long playerId, @RequestParam(required = false) Long clubId) {
        return new ResponseEntity<>(playerService.addClub(playerId, clubId), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{playerId}")
    public ResponseEntity<String> deletePlayerById(@PathVariable @Valid Long playerId) {
        return new ResponseEntity<>(playerService.deletePlayerById(playerId), HttpStatus.OK);
    }


}
