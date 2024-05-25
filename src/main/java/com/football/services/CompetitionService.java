package com.football.services;

import com.football.dtos.inDTO.CompetitionInDTO;
import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.entities.Competition;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.competitionMappers.CompetitionInDTOToCompetition;
import com.football.mappers.competitionMappers.CompetitionProjectionToCompetitionOutDTO;
import com.football.mappers.competitionMappers.CompetitionToCompetitionOutDTO;
import com.football.projections.competitionProjection.ICompetitionProjection;
import com.football.repositories.CompetitionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionInDTOToCompetition competitionInDTOToCompetition;
    private final CompetitionToCompetitionOutDTO competitionToCompetitionOutDTO;
    private final CompetitionProjectionToCompetitionOutDTO competitionProjectionToCompetitionOutDTO;

    public CompetitionService(CompetitionRepository competitionRepository,
                              CompetitionInDTOToCompetition competitionInDTOToCompetition,
                              CompetitionToCompetitionOutDTO competitionToCompetitionOutDTO,
                              CompetitionProjectionToCompetitionOutDTO competitionProjectionToCompetitionOutDTO) {
        this.competitionRepository = competitionRepository;
        this.competitionInDTOToCompetition = competitionInDTOToCompetition;
        this.competitionToCompetitionOutDTO = competitionToCompetitionOutDTO;
        this.competitionProjectionToCompetitionOutDTO = competitionProjectionToCompetitionOutDTO;
    }

    public CompetitionOutDTO createCompetition(CompetitionInDTO competitionInDTO) {
        Competition competition = competitionInDTOToCompetition.map(competitionInDTO);
        competitionRepository.save(competition);
        CompetitionOutDTO competitionOutDTO = competitionToCompetitionOutDTO.map(competition);
        return competitionOutDTO;
    }

    public CompetitionOutDTO findCompetitionById(Long competitionId) {
        ICompetitionProjection iCompetitionProjection = this.findCompetitionWithProjection(competitionId);
        CompetitionOutDTO competitionOutDTO = competitionProjectionToCompetitionOutDTO.map(iCompetitionProjection);
        return competitionOutDTO;
    }





    /*------------------------------------------------------------------------------------------------*/
    /*                                   COMPETITION: UTIlS                                           */
    /*------------------------------------------------------------------------------------------------*/


    // It is not an endpoint, it returns a Competition Entity

    public Competition findCompetition (Long competitionId) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(competitionId);
        if(optionalCompetition.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCompetition.get();
    }

    //finds and validates Competition entity exists and returns ICompetitionProjection

    public ICompetitionProjection findCompetitionWithProjection (Long competitionId) {
        Optional<ICompetitionProjection> optionalCompetition = competitionRepository.findCompetitionById(competitionId);
        if(optionalCompetition.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCompetition.get();
    }
}
