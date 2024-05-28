package com.football.mappers.clubMappers;

import com.football.dtos.outDTO.clubOutDTO.ClubsListOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.clubProjections.IClubsList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubProjectionListToClubOutDTOList implements IMapper<List<IClubsList>, List<ClubsListOutDTO> > {

    @Override
    public List<ClubsListOutDTO> map(List<IClubsList> clubsProjectionList) {

        List<ClubsListOutDTO> clubsListOutDTOS = clubsProjectionList.stream()
                .map(clubProjection -> {
                    ClubsListOutDTO clubsListOutDTO = new ClubsListOutDTO();
                    clubsListOutDTO.setClubId(clubProjection.getClubId());
                    clubsListOutDTO.setClubName(clubProjection.getClubName());
                    return clubsListOutDTO;
                })
                .collect(Collectors.toList());

        return clubsListOutDTOS;
    }
}
