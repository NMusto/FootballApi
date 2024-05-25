package com.football.mappers.competitionMappers;

import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.competitionProjection.ICompetitionProjection;
import org.springframework.stereotype.Component;

@Component
public class CompetitionProjectionToCompetitionOutDTO implements IMapper<ICompetitionProjection, CompetitionOutDTO> {

    @Override
    public CompetitionOutDTO map(ICompetitionProjection competitionProjection) {

        CompetitionOutDTO competitionOutDTO = new CompetitionOutDTO();
        competitionOutDTO.setId(competitionProjection.getId());
        competitionOutDTO.setName(competitionProjection.getName());
        competitionOutDTO.setQuantityPrice(competitionProjection.getQuantityPrice());
        competitionOutDTO.setStartDate(competitionProjection.getStartDate());
        competitionOutDTO.setEndDate(competitionProjection.getEndDate());

        return competitionOutDTO;
    }
}
