package com.football.mappers.competitionMappers;

import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.competitionProjection.ICompetitionProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompetitionProjectionListToCompetitionOutDTOList implements IMapper<List<ICompetitionProjection>, List<CompetitionOutDTO>> {

    private final CompetitionProjectionToCompetitionOutDTO competitionProjectionToCompetitionOutDTO;

    public CompetitionProjectionListToCompetitionOutDTOList(CompetitionProjectionToCompetitionOutDTO competitionProjectionToCompetitionOutDTO) {
        this.competitionProjectionToCompetitionOutDTO = competitionProjectionToCompetitionOutDTO;
    }

    @Override
    public List<CompetitionOutDTO> map(List<ICompetitionProjection> competitionProjectionList) {

        List<CompetitionOutDTO> competitionOutDTOList = competitionProjectionList.stream()
                .map(competitionProjection -> competitionProjectionToCompetitionOutDTO.map(competitionProjection))
                .collect(Collectors.toList());

        return competitionOutDTOList;
    }


}
