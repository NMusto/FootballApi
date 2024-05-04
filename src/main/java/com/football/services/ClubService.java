package com.football.services;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.entities.Club;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.ClubInDTOToClub;
import com.football.projections.IClubOutProjection;
import com.football.repositories.ClubRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new InfoExceptions("Id Inexistente", HttpStatus.NOT_FOUND);
        }
        return optionalClub.get();
    }

    // No es un endpoint, devuelve un Club para la creación del Coach en su builder
    public Club findById(Long clubId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        if(optionalClub.isEmpty()) {
            return null;
        }
        return optionalClub.get();
    }

    public Page<IClubOutProjection> findAllClubs(Pageable pageable) {
        Page<IClubOutProjection> clubs = clubRepository.findAllProjetedBy(pageable);
        if (clubs.isEmpty()) {
            throw new InfoExceptions("No existen clubs registrados actualmente", HttpStatus.NOT_FOUND);
        }
        return clubs;
    }


}
