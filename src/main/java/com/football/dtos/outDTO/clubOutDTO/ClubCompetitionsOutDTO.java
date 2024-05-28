package com.football.dtos.outDTO.clubOutDTO;

import com.football.dtos.outDTO.competitionOutDTO.CompetitionOutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubCompetitionsOutDTO {

    private Long clubId;
    private String ClubName;

    private List<CompetitionOutDTO> competitions;
}
