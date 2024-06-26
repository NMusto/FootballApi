package com.football.mappers.associationMappers;

import com.football.dtos.inDTO.AssociationInDTO;
import com.football.entities.Association;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class AssociationInDTOToAssociation implements IMapper<AssociationInDTO, Association> {

    @Override
    public Association map(AssociationInDTO associationInDTO) {
        Association association = Association.builder()
                .name(associationInDTO.getName())
                .president(associationInDTO.getPresident())
                .build();
        return association;
    }
}
