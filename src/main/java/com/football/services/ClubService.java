package com.football.services;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.dtos.outDTO.ClubOutDTO;
import com.football.entities.Club;
import com.football.mappers.ClubInDTOToClub;
import com.football.repositories.ClubRepository;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    private ClubRepository clubRepository;
    private ClubInDTOToClub clubInDTOToClub;

    private ClubService(ClubRepository clubRepository, ClubInDTOToClub clubInDTOToClub) {
        this.clubRepository = clubRepository;
        this.clubInDTOToClub = clubInDTOToClub;
    }

    public Club createClub(ClubInDTO clubInDTO) {
        Club club = clubInDTOToClub.map(clubInDTO);
        return clubRepository.save(club);
    }
}
