package com.football.mappers.clubMappers;

import com.football.dtos.outDTO.ClubOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.IClubProjection;
import org.springframework.stereotype.Component;

@Component
public class ClubProjectionToClubOutDTO implements IMapper<IClubProjection, ClubOutDTO> {
    @Override
    public ClubOutDTO map(IClubProjection clubProjection) {

        ClubOutDTO clubOutDTO = new ClubOutDTO();
        clubOutDTO.setId(clubProjection.getId());
        clubOutDTO.setName(clubProjection.getName());
        clubOutDTO.setDebt(clubProjection.getDebt());
        clubOutDTO.setAssociateNumber(clubProjection.getAssociateNumber());
        clubOutDTO.setCoachId(clubProjection.getCoachId());
        clubOutDTO.setCoachName(clubProjection.getCoachName());
        clubOutDTO.setCoachLastName(clubProjection.getCoachLastName());
        clubOutDTO.setAssociationId(clubProjection.getAssociationId());
        clubOutDTO.setAssociationName(clubProjection.getAssociationName());

        return clubOutDTO;
    }
}
