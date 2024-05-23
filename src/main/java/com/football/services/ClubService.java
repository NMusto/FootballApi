package com.football.services;

import com.football.dtos.inDTO.ClubInDTO;
import com.football.dtos.outDTO.ClubOutDTO;
import com.football.entities.Association;
import com.football.entities.Club;
import com.football.entities.Coach;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.ClubInDTOToClub;
import com.football.mappers.ClubToClubOutDTO;
import com.football.mappers.clubMappers.ClubProjectionPageToClubOutDTOPage;
import com.football.mappers.clubMappers.ClubProjectionToClubOutDTO;
import com.football.projections.IClubProjection;
import com.football.repositories.ClubRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final CoachService coachService;
    private final ClubInDTOToClub clubInDTOToClub;
    private final ClubToClubOutDTO clubToClubOutDTO;
    private final AssociationService associationService;
    private final ClubProjectionToClubOutDTO clubProjectionToClubOutDTO;
    private final ClubProjectionPageToClubOutDTOPage clubProjectionPageToClubOutDTOPage;

    public ClubService(ClubRepository clubRepository, CoachService coachService,
                       ClubInDTOToClub clubInDTOToClub, ClubToClubOutDTO clubToClubOutDTO,
                       AssociationService associationService,
                       ClubProjectionToClubOutDTO clubProjectionToClubOutDTO,
                       ClubProjectionPageToClubOutDTOPage clubProjectionPageToClubOutDTOPage) {
        this.clubRepository = clubRepository;
        this.clubInDTOToClub = clubInDTOToClub;
        this.clubToClubOutDTO = clubToClubOutDTO;
        this.coachService = coachService;
        this.associationService = associationService;
        this.clubProjectionToClubOutDTO = clubProjectionToClubOutDTO;
        this.clubProjectionPageToClubOutDTOPage = clubProjectionPageToClubOutDTOPage;
    }

    public ClubOutDTO createClub(ClubInDTO clubInDTO) {
        Club club = clubInDTOToClub.map(clubInDTO);
        clubRepository.save(club);
        ClubOutDTO clubOutDTO = clubToClubOutDTO.map(club);
        return clubOutDTO;
    }

    public ClubOutDTO findClubById(Long clubId) {
        IClubProjection iClubProjection = this.findClubWithProjection(clubId);
        ClubOutDTO clubOutDTO = clubProjectionToClubOutDTO.map(iClubProjection);
        return clubOutDTO;
    }

    public Page<ClubOutDTO> findAllClubs(Pageable pageable) {
        Page<IClubProjection> clubsProjection = clubRepository.findAllProjetedBy(pageable);
        if (clubsProjection.isEmpty()) {
            throw new InfoExceptions( "There are currently no registered clubs.", HttpStatus.NOT_FOUND);
        }
        return clubProjectionPageToClubOutDTOPage.map(clubsProjection);
    }

    public ClubOutDTO updateClubById(Long clubId, ClubInDTO clubInDTO) {
        Club club = this.findClub(clubId);

        club.setName(clubInDTO.getName());
        club.setAssociateNumber(clubInDTO.getAssociateNumber());
        clubRepository.save(club);

        IClubProjection iClubProjection = this.findClubWithProjection(clubId);
        ClubOutDTO clubOutDTO = clubProjectionToClubOutDTO.map(iClubProjection);
        return clubOutDTO;
    }

    public String addCoach(Long clubId, Long coachId) {

        Club club = this.findClub(clubId);

        if (coachId == null) {
            club.setCoach(null);
        }
        else {
            Coach coach = coachService.findCoach(coachId);
            club.setCoach(coach);
        }
        clubRepository.save(club);
        return "Coach id: " + coachId + " successfully added to Club id: " + clubId;
    }

    public String addAssociation (Long clubId, Long associationId) {

        Club club = this.findClub(clubId);

        if (associationId == null) {
            club.setAssociation(null);
        }
        else {
            Association association = associationService.findAssociation(associationId);
            club.setAssociation(association);
        }
        clubRepository.save(club);
        return "Association id: " + associationId + " successfully added to Club id: " + clubId;
    }

    public String deleteClubById(Long clubId) {
        this.findClub(clubId);
        clubRepository.deleteById(clubId);
        return "Club id: " + clubId + " was successfully deleted.";
    }



    /*------------------------------------------------------------------------------------------------*/
    /*                                    CLUB: UTIlS                                                 */
    /*------------------------------------------------------------------------------------------------*/


    // It is not an endpoint, it returns a Club Entity

    public Club findClub (Long clubId) {
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        if(optionalClub.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalClub.get();
    }

    //finds and validates Club entity exists and returns IClubProjection

    public IClubProjection findClubWithProjection (Long clubId) {
        Optional<IClubProjection> optionalClub = clubRepository.findClubById(clubId);
        if(optionalClub.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalClub.get();
    }




}
