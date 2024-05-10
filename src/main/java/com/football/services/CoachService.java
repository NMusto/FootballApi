package com.football.services;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.dtos.inDTO.CoachInDTO;
import com.football.dtos.outDTO.CoachOutDTO;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.CoachInDTOToCoach;
import com.football.mappers.CoachToCoachOutDTO;
import com.football.projections.IClubCoachProjection;
import com.football.projections.ICoachClubProjection;
import com.football.repositories.CoachRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // No es un endpoint, devuelve un Coach para manejo interno de la app
    public Coach findById(Long coachId) {
        Optional<Coach> optionalCoach = coachRepository.findById(coachId);
        if (optionalCoach.isEmpty()) {
            return null;
        }
        return optionalCoach.get();
    }

    public List<ICoachClubProjection> findAllCoaches() {
        List<ICoachClubProjection> coaches = coachRepository.findAllProjectedBy();
        if (coaches.isEmpty()) {
            throw new InfoExceptions("No existen coaches registrados actualmente", HttpStatus.NOT_FOUND);
        }
        return coaches;
    }

    public ICoachClubProjection updateCoachById(Long coachId, CoachInDTO coachInDTO) {
        Optional<Coach> optionalCoach = coachRepository.findById(coachId);
        if (optionalCoach.isEmpty()) {
            throw new InfoExceptions("Id inexistente!", HttpStatus.NOT_FOUND);
        }
        Coach coach = optionalCoach.get();
        coach.setName(coachInDTO.getName());
        coach.setLastName(coachInDTO.getLastName());
        coach.setNationality(coachInDTO.getNationality());
        coach.setAge(coachInDTO.getAge());

        coachRepository.save(coach);
        Optional<ICoachClubProjection> coachOptional = coachRepository.findCoachById(coachId);
        return coachOptional.get();
    }

}
