package com.football.mappers;

import com.football.dtos.outDTO.AssociationOutDTO;
import com.football.entities.Association;
import org.springframework.stereotype.Component;

@Component
public class AssociationToAssociationOutDTO implements IMapper<Association, AssociationOutDTO> {


    @Override
    public AssociationOutDTO map(Association association) {
        AssociationOutDTO associationOutDTO = new AssociationOutDTO();
        associationOutDTO.setId(association.getId());
        associationOutDTO.setName(association.getName());
        associationOutDTO.setPresident(association.getPresident());

        return associationOutDTO;
    }
}
