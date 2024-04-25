package com.football.mappers;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.entities.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ClubInDTOToClub implements IMapper<ClubInDTO, Club> {

    @Override
    public Club map(ClubInDTO clubInDTO) {
        Club club = Club.builder()
                .name(clubInDTO.getName())
                .debt(false)
                .coach(Coach.builder()
                        .name(clubInDTO.getCoachInDTO().getName())
                        .lastName(clubInDTO.getCoachInDTO().getLastName())
                        .nationality(clubInDTO.getCoachInDTO().getNationality())
                        .age(clubInDTO.getCoachInDTO().getAge())
                        .build())
                .build();
        return club;
    }
}
