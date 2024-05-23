package com.football.controllers;

import com.football.dtos.inDTO.AssociationInDTO;
import com.football.dtos.inDTO.CoachInDTO;
import com.football.dtos.outDTO.CoachOutDTO;
import com.football.dtos.outDTO.associationOutDTO.AssociationClubsOutDTO;
import com.football.dtos.outDTO.associationOutDTO.AssociationOutDTO;
import com.football.services.AssociationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/association")
public class AssociationController {

    private final AssociationService associationService;

    public AssociationController(AssociationService associationService) {
        this.associationService = associationService;
    }

    @PostMapping("/create")
    public ResponseEntity<AssociationOutDTO> createAssociation(@RequestBody @Valid AssociationInDTO associationInDTO) {
        return new ResponseEntity<>(associationService.createAssociation(associationInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findassociation/{associationId}")
    public ResponseEntity<AssociationOutDTO> findAssociationById(@PathVariable @Valid Long associationId) {
        return new ResponseEntity<>(associationService.findAssociationById(associationId),HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<Page<AssociationOutDTO>> findAllAssociations(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "10") int size,
                                                                            @RequestParam(defaultValue = "name") String name) {
        Page<AssociationOutDTO> associations = associationService.findAllAssociations(PageRequest.of(page, size, Sort.by(name).ascending()));
        return new ResponseEntity<>(associations, HttpStatus.OK);
    }

    @GetMapping("/findclubs/{associationId}")
    public ResponseEntity<AssociationClubsOutDTO> findClubsByAssociationId(@PathVariable @Valid Long associationId) {
        return new ResponseEntity<>(associationService.findAllClubs(associationId), HttpStatus.OK);
    }

    @PutMapping("/update/{associationId}")
    public ResponseEntity<AssociationOutDTO> updatAssociation(@PathVariable @Valid Long associationId, @RequestBody @Valid AssociationInDTO associationInDTO) {
        return new ResponseEntity<>(associationService.updateAssociationById(associationId, associationInDTO), HttpStatus.OK);
    }



}
