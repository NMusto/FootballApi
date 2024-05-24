package com.football.dtos.outDTO.associationOutDTO;

import com.football.dtos.outDTO.clubOutDTO.ClubsListOutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationClubsOutDTO {

    private Long associationId;
    private String associationName;

    private List<ClubsListOutDTO> clubs;
}
