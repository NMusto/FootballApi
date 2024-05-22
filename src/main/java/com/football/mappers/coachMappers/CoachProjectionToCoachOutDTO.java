package com.football.mappers.coachMappers;

import com.football.dtos.outDTO.CoachOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.ICoachProjection;
import org.springframework.stereotype.Component;

@Component
public class CoachProjectionToCoachOutDTO implements IMapper<ICoachProjection, CoachOutDTO> {
    @Override
    public CoachOutDTO map(ICoachProjection coachProjection) {
        CoachOutDTO coachOutDTO = new CoachOutDTO();
        coachOutDTO.setId(coachProjection.getId());
        coachOutDTO.setName(coachProjection.getName());
        coachOutDTO.setLastName(coachProjection.getLastName());
        coachOutDTO.setNationality(coachProjection.getNationality());
        coachOutDTO.setAge(coachProjection.getAge());
        coachOutDTO.setClubId(coachProjection.getClubId());
        coachOutDTO.setClubName(coachProjection.getClubName());

        return coachOutDTO;
    }
}
