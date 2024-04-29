package com.football.services;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.entities.Coach;
import com.football.mappers.CoachInDTOToCoach;
import com.football.repositories.CoachRepository;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

    private CoachRepository coachRepository;
    private CoachInDTOToCoach coachInDTOToCoach;


    public CoachService(CoachRepository coachRepository, CoachInDTOToCoach coachInDTOToCoach) {
        this.coachRepository = coachRepository;
        this.coachInDTOToCoach = coachInDTOToCoach;
    }

    public Coach createCoach(CoachInDTO coachInDTO) {
        Coach coach = coachInDTOToCoach.map(coachInDTO);
         return coachRepository.save(coach);
    }
}
