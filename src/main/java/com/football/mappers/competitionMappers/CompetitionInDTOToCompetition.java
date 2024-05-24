package com.football.mappers.competitionMappers;

import com.football.dtos.inDTO.CompetitionInDTO;
import com.football.entities.Competition;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class CompetitionInDTOToCompetition implements IMapper<CompetitionInDTO, Competition> {

    @Override
    public Competition map(CompetitionInDTO competitionInDTO) {
        Competition competition = Competition.builder()
                .name(competitionInDTO.getName())
                .quantityPrice(competitionInDTO.getQuantityPrice())
                .startDate(competitionInDTO.getStartDate())
                .endDate(competitionInDTO.getEndDate())
                .build();
        return competition;
    }
}
