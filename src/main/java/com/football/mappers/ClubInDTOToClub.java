package com.football.mappers;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.entities.Club;
import org.springframework.stereotype.Component;

@Component
public class ClubInDTOToClub implements IMapper<ClubInDTO, Club> {

    @Override
    public Club map(ClubInDTO clubInDTO) {
        Club club = Club.builder()
                .name(clubInDTO.getName())
                .debt(false)
                .associationNumber(clubInDTO.getAssociationNumber())
                .build();
        return club;
    }


}
