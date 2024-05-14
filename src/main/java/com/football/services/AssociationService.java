package com.football.services;

import com.football.dtos.inDTO.AssociationInDTO;
import com.football.dtos.outDTO.AssociationOutDTO;
import com.football.entities.Association;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.AssociationInDTOToAssociation;
import com.football.mappers.AssociationToAssociationOutDTO;
import com.football.projections.IAssociationProjection;
import com.football.repositories.AssociationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociationService {

    private final AssociationRepository associationRepository;
    private final AssociationInDTOToAssociation associationInDTOToAssociation;
    private final AssociationToAssociationOutDTO associationToAssociationOutDTO;

    public AssociationService(AssociationRepository associationRepository, AssociationToAssociationOutDTO associationToAssociationOutDTO,
                              AssociationInDTOToAssociation associationInDTOToAssociation) {
        this.associationRepository = associationRepository;
        this.associationInDTOToAssociation = associationInDTOToAssociation;
        this.associationToAssociationOutDTO = associationToAssociationOutDTO;
    }

    public AssociationOutDTO createAssociation(AssociationInDTO associationInDTO) {
        Association association = associationInDTOToAssociation.map(associationInDTO);
        associationRepository.save(association);
        AssociationOutDTO associationOutDTO = associationToAssociationOutDTO.map(association);
        return associationOutDTO;
    }

    public IAssociationProjection findAssociationById(Long associationId) {
        IAssociationProjection iAssociationProjection = this.findAssociationWithProjection(associationId);
        return iAssociationProjection;
    }

    public Page<IAssociationProjection> findAllAssociations(Pageable pageable) {
        Page<IAssociationProjection> associations = associationRepository.findAllProjectedBy(pageable);
        if (associations.isEmpty()) {
            throw new InfoExceptions( "There are currently no registered associations.", HttpStatus.NOT_FOUND);
        }
        return associations;
    }



    /*------------------------------------------------------------------------------------------------*/
    /*                                   ASSOCIATION: UTIlS                                           */
    /*------------------------------------------------------------------------------------------------*/


    //finds and validates Association entity exists and returns IAssociationProjection

    public IAssociationProjection findAssociationWithProjection(Long associationId) {
        Optional<IAssociationProjection> iAssociationProjection = associationRepository.findAssociationById(associationId);
        if(iAssociationProjection.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return iAssociationProjection.get();
    }

}
