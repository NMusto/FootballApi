package com.football.mappers;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.services.ClubService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CoachInDTOToCoach implements IMapper<CoachInDTO, Coach> {

    private ClubService clubService;

    public CoachInDTOToCoach(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public Coach map(CoachInDTO coachInDTO) {

        Coach coach = Coach.builder()
                .name(coachInDTO.getName())
                .lastName(coachInDTO.getLastName())
                .nationality(coachInDTO.getNationality())
                .age(coachInDTO.getAge())
                .build();
        return coach;
    }
}
