package com.football.services;

import com.football.dtos.inDTO.AssociationInDTO;
import com.football.dtos.outDTO.clubOutDTO.ClubsListOutDTO;
import com.football.dtos.outDTO.associationOutDTO.AssociationClubsOutDTO;
import com.football.dtos.outDTO.associationOutDTO.AssociationOutDTO;
import com.football.entities.Association;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.associationMappers.AssociationInDTOToAssociation;
import com.football.mappers.associationMappers.AssociationToAssociationOutDTO;
import com.football.mappers.associationMappers.AssociationProjectionPageToAssociationOutDTOPage;
import com.football.mappers.associationMappers.AssociationProjectionToAssociationOutDTO;
import com.football.mappers.clubMappers.ClubProjectionListToClubOutDTOList;
import com.football.projections.associationProjections.IAssociationProjection;
import com.football.projections.clubProjections.IClubsList;
import com.football.repositories.AssociationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {

    private final AssociationRepository associationRepository;
    private final AssociationInDTOToAssociation associationInDTOToAssociation;
    private final AssociationToAssociationOutDTO associationToAssociationOutDTO;
    private final AssociationProjectionToAssociationOutDTO associationProjectionToAssociationOutDTO;
    private final AssociationProjectionPageToAssociationOutDTOPage AssociationProjectionPageToAssociationOutDTOPage;
    private final ClubProjectionListToClubOutDTOList clubProjectionListToClubOutDTOList;

    public AssociationService(AssociationRepository associationRepository, AssociationToAssociationOutDTO associationToAssociationOutDTO,
                              AssociationInDTOToAssociation associationInDTOToAssociation,
                              AssociationProjectionToAssociationOutDTO associationProjectionToAssociationOutDTO,
                              AssociationProjectionPageToAssociationOutDTOPage AssociationProjectionPageToAssociationOutDTOPage,
                              ClubProjectionListToClubOutDTOList clubProjectionListToClubOutDTOList) {
        this.associationRepository = associationRepository;
        this.associationInDTOToAssociation = associationInDTOToAssociation;
        this.associationToAssociationOutDTO = associationToAssociationOutDTO;
        this.associationProjectionToAssociationOutDTO = associationProjectionToAssociationOutDTO;
        this.AssociationProjectionPageToAssociationOutDTOPage = AssociationProjectionPageToAssociationOutDTOPage;
        this.clubProjectionListToClubOutDTOList = clubProjectionListToClubOutDTOList;
    }

    public AssociationOutDTO createAssociation(AssociationInDTO associationInDTO) {
        Association association = associationInDTOToAssociation.map(associationInDTO);
        associationRepository.save(association);
        AssociationOutDTO associationOutDTO = associationToAssociationOutDTO.map(association);
        return associationOutDTO;
    }

    public AssociationOutDTO findAssociationById(Long associationId) {
        IAssociationProjection associationProjection = this.findAssociationWithProjection(associationId);
        AssociationOutDTO associationOutDTO = associationProjectionToAssociationOutDTO.map(associationProjection);
        return associationOutDTO;
    }

    public Page<AssociationOutDTO> findAllAssociations(Pageable pageable) {
        Page<IAssociationProjection> associationsProjection = associationRepository.findAllProjectedBy(pageable);
        if (associationsProjection.isEmpty()) {
            throw new InfoExceptions( "There are currently no registered associations.", HttpStatus.NOT_FOUND);
        }
        return AssociationProjectionPageToAssociationOutDTOPage.map(associationsProjection);
    }

    public AssociationClubsOutDTO findAllClubs(Long associationId) {

        Association association = this.findAssociation(associationId);

        AssociationClubsOutDTO associationClubsOutDTO = new AssociationClubsOutDTO();

        associationClubsOutDTO.setAssociationId(associationId);
        associationClubsOutDTO.setAssociationName(association.getName());

        List<IClubsList> clubsProjections = associationRepository.findAllClubs(associationId);

        List<ClubsListOutDTO> clubsListOutDTOS = clubProjectionListToClubOutDTOList.map(clubsProjections);

        associationClubsOutDTO.setClubs(clubsListOutDTOS);

        return associationClubsOutDTO;
    }

    public AssociationOutDTO updateAssociationById(Long associationId, AssociationInDTO associationInDTO) {
        Association association = this.findAssociation(associationId);

        association.setName(associationInDTO.getName());
        association.setPresident(associationInDTO.getPresident());
        associationRepository.save(association);

        IAssociationProjection iAssociationProjection = this.findAssociationWithProjection(associationId);
        AssociationOutDTO associationOutDTO = associationProjectionToAssociationOutDTO.map(iAssociationProjection);
        return associationOutDTO;
    }

    @Transactional
    public String deleteAssociationById(Long associatioId) {
        this.findAssociation(associatioId);

        associationRepository.deleteAssociationIdInClubs(associatioId);
        associationRepository.deleteById(associatioId);
        return "Association id: " + associatioId + " was successfully deleted.";
    }





    /*------------------------------------------------------------------------------------------------*/
    /*                                   ASSOCIATION: UTIlS                                           */
    /*------------------------------------------------------------------------------------------------*/



    // It is not an endpoint, it returns an Association Entity

    public Association findAssociation (Long associationId) {
        Optional<Association> optionalAssociation = associationRepository.findById(associationId);
        if(optionalAssociation.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalAssociation.get();
    }


    //finds and validates Association entity exists and returns IAssociationProjection

    public IAssociationProjection findAssociationWithProjection(Long associationId) {
        Optional<IAssociationProjection> optionalAssociationProjection = associationRepository.findAssociationById(associationId);
        if(optionalAssociationProjection.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalAssociationProjection.get();
    }

}
