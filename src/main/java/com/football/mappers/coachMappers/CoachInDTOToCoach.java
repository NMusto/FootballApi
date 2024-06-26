package com.football.mappers.coachMappers;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.mappers.IMapper;
import com.football.services.ClubService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CoachInDTOToCoach implements IMapper<CoachInDTO, Coach> {


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
