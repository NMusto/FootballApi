package com.football.services;

import com.football.dtos.inDTO.CompetitionInDTO;
import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.entities.Competition;
import com.football.mappers.competitionMappers.CompetitionInDTOToCompetition;
import com.football.mappers.competitionMappers.CompetitionToCompetitionOutDTO;
import com.football.repositories.CompetitionRepository;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionInDTOToCompetition competitionInDTOToCompetition;
    private final CompetitionToCompetitionOutDTO competitionToCompetitionOutDTO;

    public CompetitionService(CompetitionRepository competitionRepository,
                              CompetitionInDTOToCompetition competitionInDTOToCompetition,
                              CompetitionToCompetitionOutDTO competitionToCompetitionOutDTO) {
        this.competitionRepository = competitionRepository;
        this.competitionInDTOToCompetition = competitionInDTOToCompetition;
        this.competitionToCompetitionOutDTO = competitionToCompetitionOutDTO;
    }

    public CompetitionOutDTO createCompetition(CompetitionInDTO competitionInDTO) {
        Competition competition = competitionInDTOToCompetition.map(competitionInDTO);
        competitionRepository.save(competition);
        CompetitionOutDTO competitionOutDTO = competitionToCompetitionOutDTO.map(competition);
        return competitionOutDTO;
    }
}
