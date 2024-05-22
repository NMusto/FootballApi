package com.football.mappers.associationMappers;

import com.football.dtos.outDTO.associationOutDTO.AssociationOutDTO;
import com.football.entities.Association;
import com.football.mappers.IMapper;
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
