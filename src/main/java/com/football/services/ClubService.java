package com.football.services;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.dtos.outDTO.ClubOutDTO;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.ClubInDTOToClub;
import com.football.mappers.ClubToClubOutDTO;
import com.football.projections.IClubCoachProjection;
import com.football.repositories.ClubRepository;
import com.football.repositories.CoachRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final CoachRepository coachRepository;
    private final ClubInDTOToClub clubInDTOToClub;
    private final ClubToClubOutDTO clubToClubOutDTO;

    public ClubService(ClubRepository clubRepository, CoachRepository coachRepository,
                       ClubInDTOToClub clubInDTOToClub, ClubToClubOutDTO clubToClubOutDTO) {
        this.clubRepository = clubRepository;
        this.clubInDTOToClub = clubInDTOToClub;
        this.clubToClubOutDTO = clubToClubOutDTO;
        this.coachRepository = coachRepository;
    }

    public ClubOutDTO createClub(ClubInDTO clubInDTO) {
        Club club = clubInDTOToClub.map(clubInDTO);
        clubRepository.save(club);
        ClubOutDTO clubOutDTO = clubToClubOutDTO.map(club);
        return clubOutDTO;
    }

    public IClubCoachProjection findClubById(Long clubId) {
        Optional<IClubCoachProjection> optionalClub = clubRepository.findClubById(clubId);
        if(optionalClub.isEmpty()) {
            throw new InfoExceptions("Id Inexistente", HttpStatus.NOT_FOUND);
        }
        return optionalClub.get();
    }

    // No es un endpoint, devuelve un Club para manejo interno de la app
    public Club findById(Long clubId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        if(optionalClub.isEmpty()) {
            return null;
        }
        return optionalClub.get();
    }

    public Page<IClubCoachProjection> findAllClubs(Pageable pageable) {
        Page<IClubCoachProjection> clubs = clubRepository.findAllProjetedBy(pageable);
        if (clubs.isEmpty()) {
            throw new InfoExceptions("No existen clubs registrados actualmente", HttpStatus.NOT_FOUND);
        }
        return clubs;
    }

    public IClubCoachProjection updateClubById(Long clubId, ClubInDTO clubInDTO) {
        Optional<Club> optionalclub = clubRepository.findById(clubId);
        if (optionalclub.isEmpty()) {
            throw new InfoExceptions("Id inexistente!", HttpStatus.NOT_FOUND);
        }
        Club club = optionalclub.get();
        club.setName(clubInDTO.getName());
        club.setAssociationNumber(clubInDTO.getAssociationNumber());

        clubRepository.save(club);
        Optional<IClubCoachProjection> clubOptional = clubRepository.findClubById(clubId);
        return clubOptional.get();
    }

    public String addCoach(Long clubId, Long coachId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        if (optionalClub.isEmpty()) {
            throw new InfoExceptions("Club inexistente!", HttpStatus.NOT_FOUND);
        }
        Optional<Coach> optionalCoach = coachRepository.findById(coachId);
        if (optionalCoach.isEmpty()) {
            throw new InfoExceptions("Coach inexistente!", HttpStatus.NOT_FOUND);
        }
        Coach coach = optionalCoach.get();

        Club club = optionalClub.get();
        club.setCoach(coach);

        clubRepository.save(club);
        return "Coach id: " + coachId + " agregado exitosamente al Club id: " + clubId;
    }

}
