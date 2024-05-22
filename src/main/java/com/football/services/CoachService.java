package com.football.services;

import com.football.dtos.inDTO.CoachInDTO;
import com.football.dtos.outDTO.CoachOutDTO;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.coachMappers.CoachInDTOToCoach;
import com.football.mappers.coachMappers.CoachProjectionListToCoachOutDTOList;
import com.football.mappers.coachMappers.CoachProjectionToCoachOutDTO;
import com.football.mappers.coachMappers.CoachToCoachOutDTO;
import com.football.projections.ICoachProjection;
import com.football.repositories.ClubRepository;
import com.football.repositories.CoachRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    private final ClubRepository clubRepository;
    private final CoachInDTOToCoach coachInDTOToCoach;
    private final CoachToCoachOutDTO coachToCoachOutDTO;
    private final CoachProjectionToCoachOutDTO coachProjectionToCoachOutDTO;
    private final CoachProjectionListToCoachOutDTOList coachProjectionListToCoachOutDTOList;


    public CoachService(CoachRepository coachRepository, ClubRepository clubRepository,
                        CoachInDTOToCoach coachInDTOToCoach, CoachToCoachOutDTO coachToCoachOutDTO,
                        CoachProjectionToCoachOutDTO coachProjectionToCoachOutDTO,
                        CoachProjectionListToCoachOutDTOList coachProjectionListToCoachOutDTOList) {
        this.coachRepository = coachRepository;
        this.coachInDTOToCoach = coachInDTOToCoach;
        this.coachToCoachOutDTO = coachToCoachOutDTO;
        this.clubRepository = clubRepository;
        this.coachProjectionToCoachOutDTO = coachProjectionToCoachOutDTO;
        this.coachProjectionListToCoachOutDTOList = coachProjectionListToCoachOutDTOList;
    }

    public CoachOutDTO createCoach(CoachInDTO coachInDTO) {
        Coach coach = coachInDTOToCoach.map(coachInDTO);
        coachRepository.save(coach);
        CoachOutDTO coachOutDTO = coachToCoachOutDTO.map(coach);
        return coachOutDTO;
    }

    public CoachOutDTO findCoachById(Long coachId) {
        ICoachProjection iCoachProjection = this.findCoachWithProjection(coachId);
        CoachOutDTO coachOutDTO = coachProjectionToCoachOutDTO.map(iCoachProjection);
        return coachOutDTO;
    }

    public List<CoachOutDTO> findAllCoaches() {
        List<ICoachProjection> coachProjectionList = coachRepository.findAllProjectedBy();
        if (coachProjectionList.isEmpty()) {
            throw new InfoExceptions("There are currently no registered coaches.", HttpStatus.NOT_FOUND);
        }
        List<CoachOutDTO> coachOutDTOList = coachProjectionListToCoachOutDTOList.map(coachProjectionList);
        return coachOutDTOList;
    }

    public CoachOutDTO updateCoachById(Long coachId, CoachInDTO coachInDTO) {
        Coach coach = this.findCoach(coachId);

        coach.setName(coachInDTO.getName());
        coach.setLastName(coachInDTO.getLastName());
        coach.setNationality(coachInDTO.getNationality());
        coach.setAge(coachInDTO.getAge());
        coachRepository.save(coach);

        ICoachProjection iCoachProjection = this.findCoachWithProjection(coachId);
        CoachOutDTO coachOutDTO = coachProjectionToCoachOutDTO.map(iCoachProjection);
        return coachOutDTO;
    }

    public String deleteCoachById(Long coachId) {
        Coach coach = this.findCoach(coachId);
        Club club = coach.getClub();
        club.setCoach(null);
        this.clubRepository.save(club);

        coachRepository.deleteById(coachId);
        return "Coach id: " + coachId + " was successfully deleted.";
    }



    /*------------------------------------------------------------------------------------------------*/
    /*                                  COACH: UTIlS                                                  */
    /*------------------------------------------------------------------------------------------------*/


    // It is not an endpoint, it returns a Coach Entity

    public Coach findCoach(Long coachId) {
        Optional<Coach> optionalCoach = coachRepository.findById(coachId);
        if (optionalCoach.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCoach.get();
    }

    //finds and validates Coach entity exists and returns ICoachProjection

    public ICoachProjection findCoachWithProjection (Long clubId) {
        Optional<ICoachProjection> optionalCoach = coachRepository.findCoachById(clubId);
        if(optionalCoach.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCoach.get();
    }

}
