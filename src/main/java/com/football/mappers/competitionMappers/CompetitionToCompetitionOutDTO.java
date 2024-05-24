package com.football.mappers.competitionMappers;

import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.entities.Competition;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class CompetitionToCompetitionOutDTO implements IMapper<Competition, CompetitionOutDTO> {

    @Override
    public CompetitionOutDTO map(Competition competition) {
        CompetitionOutDTO competitionOutDTO = new CompetitionOutDTO();
        competitionOutDTO.setId(competition.getId());
        competitionOutDTO.setName(competition.getName());
        competitionOutDTO.setQuantityPrice(competition.getQuantityPrice());
        competitionOutDTO.setStartDate(competition.getStartDate());
        competitionOutDTO.setEndDate(competition.getEndDate());

        return competitionOutDTO;
    }
}
