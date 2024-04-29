package com.football.services;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.entities.Club;
import com.football.mappers.ClubInDTOToClub;
import com.football.projections.IClubOutProjection;
import com.football.repositories.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubInDTOToClub clubInDTOToClub;

    public ClubService(ClubRepository clubRepository, ClubInDTOToClub clubInDTOToClub) {
        this.clubRepository = clubRepository;
        this.clubInDTOToClub = clubInDTOToClub;
    }

    public Club createClub(ClubInDTO clubInDTO) {
        Club club = clubInDTOToClub.map(clubInDTO);
        return clubRepository.save(club);
    }

    public IClubOutProjection findClubById(Long clubId) {
        Optional<IClubOutProjection> optionalClub = clubRepository.findClubById(clubId);
        if(optionalClub.isEmpty()) {
            return null;
        }
        return optionalClub.get();
    }

    public Club findById(Long clubId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        if(optionalClub.isEmpty()) {
            return null;
        }
        return optionalClub.get();
    }


}
