package com.football.mappers.coachMappers;

import com.football.dtos.outDTO.coachOutDTO.CoachOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.ICoachProjection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoachProjectionListToCoachOutDTOList implements IMapper<List<ICoachProjection>, List<CoachOutDTO> > {

    private final CoachProjectionToCoachOutDTO coachProjectionToCoachOutDTO;

    public CoachProjectionListToCoachOutDTOList(CoachProjectionToCoachOutDTO coachProjectionToCoachOutDTO) {
        this.coachProjectionToCoachOutDTO = coachProjectionToCoachOutDTO;
    }

    @Override
    public List<CoachOutDTO> map(List<ICoachProjection> coachProjectionList) {

        List<CoachOutDTO> coachOutDTOList = coachProjectionList.stream()
                .map(coachProjection -> coachProjectionToCoachOutDTO.map(coachProjection))
                .collect(Collectors.toList());

        return coachOutDTOList;
    }


}
