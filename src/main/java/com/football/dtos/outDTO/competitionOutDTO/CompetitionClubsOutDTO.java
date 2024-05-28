package com.football.dtos.outDTO.competitionOutDTO;

import com.football.dtos.outDTO.clubOutDTO.ClubsListOutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionClubsOutDTO {

    private Long competitionId;
    private String competitionName;

    private List<ClubsListOutDTO> clubs;
}
