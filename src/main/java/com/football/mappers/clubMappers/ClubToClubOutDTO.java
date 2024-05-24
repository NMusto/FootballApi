package com.football.mappers.clubMappers;

import com.football.dtos.outDTO.clubOutDTO.ClubOutDTO;
import com.football.entities.Club;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class ClubToClubOutDTO implements IMapper<Club, ClubOutDTO> {

    @Override
    public ClubOutDTO map(Club club) {
        ClubOutDTO clubOutDTO = new ClubOutDTO();
        clubOutDTO.setId(club.getId());
        clubOutDTO.setName(club.getName());
        clubOutDTO.setDebt(club.isDebt());
        clubOutDTO.setAssociateNumber(club.getAssociateNumber());

        return clubOutDTO;
    }
}
