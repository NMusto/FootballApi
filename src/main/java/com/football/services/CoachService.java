package com.football.services;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.dtos.outDTO.CoachOutDTO;
import com.football.entities.Coach;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.CoachInDTOToCoach;
import com.football.mappers.CoachToCoachOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.ICoachClubProjection;
import com.football.projections.ICoachOutProjection;
import com.football.repositories.CoachRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    private final CoachInDTOToCoach coachInDTOToCoach;
    private final CoachToCoachOutDTO coachToCoachOutDTO;


    public CoachService(CoachRepository coachRepository, CoachInDTOToCoach coachInDTOToCoach, CoachToCoachOutDTO coachToCoachOutDTO) {
        this.coachRepository = coachRepository;
        this.coachInDTOToCoach = coachInDTOToCoach;
        this.coachToCoachOutDTO = coachToCoachOutDTO;
    }

    public CoachOutDTO createCoach(CoachInDTO coachInDTO) {
        Coach coach = coachInDTOToCoach.map(coachInDTO);
        coachRepository.save(coach);
        CoachOutDTO coachOutDTO = coachToCoachOutDTO.map(coach);
        return coachOutDTO;
    }

    public ICoachClubProjection findCoachById(Long coachId) {
        Optional<ICoachClubProjection> optionalCoach = coachRepository.findCoachById(coachId);
        if (optionalCoach.isEmpty()) {
            throw new InfoExceptions("Id inexistente!", HttpStatus.NOT_FOUND);
        }
        return optionalCoach.get();
    }
}
