package com.football.mappers.clubMappers;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.entities.Club;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class ClubInDTOToClub implements IMapper<ClubInDTO, Club> {

    @Override
    public Club map(ClubInDTO clubInDTO) {
        Club club = Club.builder()
                .name(clubInDTO.getName())
                .debt(false)
                .associateNumber(clubInDTO.getAssociateNumber())
                .build();
        return club;
    }


}
