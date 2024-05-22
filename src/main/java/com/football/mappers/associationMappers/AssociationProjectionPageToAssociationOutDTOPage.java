package com.football.mappers.associationMappers;

import com.football.dtos.outDTO.associationOutDTO.AssociationOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.IAssociationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociationProjectionPageToAssociationOutDTOPage implements IMapper<Page<IAssociationProjection>, Page<AssociationOutDTO>> {
    @Override
    public Page<AssociationOutDTO> map(Page<IAssociationProjection> associationProjections) {
        //Convert
        List<AssociationOutDTO> associationOutDTOList = associationProjections.stream()
                .map(associationProjection -> convertAssociationProjectionToOutDTO(associationProjection))
                .collect(Collectors.toList());

        return new PageImpl<>(associationOutDTOList,associationProjections.getPageable(), associationProjections.getTotalElements());
    }

    public AssociationOutDTO convertAssociationProjectionToOutDTO(IAssociationProjection associationProjection) {
        AssociationOutDTO associationOutDTO = new AssociationOutDTO();

        associationOutDTO.setId(associationProjection.getId());
        associationOutDTO.setName(associationProjection.getName());
        associationOutDTO.setPresident(associationProjection.getPresident());

        return associationOutDTO;
    }
}
