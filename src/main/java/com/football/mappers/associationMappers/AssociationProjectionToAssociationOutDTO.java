package com.football.mappers.associationMappers;

import com.football.dtos.outDTO.associationOutDTO.AssociationOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.associationProjections.IAssociationProjection;
import org.springframework.stereotype.Component;

@Component
public class AssociationProjectionToAssociationOutDTO implements IMapper<IAssociationProjection, AssociationOutDTO> {
    @Override
    public AssociationOutDTO map(IAssociationProjection associationProjection) {
        AssociationOutDTO associationOutDTO = new AssociationOutDTO();
        associationOutDTO.setId(associationProjection.getId());
        associationOutDTO.setName(associationProjection.getName());
        associationOutDTO.setPresident(associationProjection.getPresident());
        return associationOutDTO;
    }
}
