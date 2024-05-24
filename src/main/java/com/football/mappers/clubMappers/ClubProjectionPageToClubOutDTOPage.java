package com.football.mappers.clubMappers;

import com.football.dtos.outDTO.clubOutDTO.ClubOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.IClubProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubProjectionPageToClubOutDTOPage implements IMapper<Page<IClubProjection>, Page<ClubOutDTO> > {

    private final ClubProjectionToClubOutDTO clubProjectionToClubOutDTO;

    public ClubProjectionPageToClubOutDTOPage(ClubProjectionToClubOutDTO clubProjectionToClubOutDTO) {
        this.clubProjectionToClubOutDTO = clubProjectionToClubOutDTO;
    }


    @Override
    public Page<ClubOutDTO> map(Page<IClubProjection> clubProjections) {

        List<ClubOutDTO> clubOutDTOList = clubProjections.stream()
                .map(clubProjection -> clubProjectionToClubOutDTO.map(clubProjection))
                .collect(Collectors.toList());

        return new PageImpl<>(clubOutDTOList, clubProjections.getPageable(), clubProjections.getTotalElements());
    }
}
