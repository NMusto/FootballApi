package com.football.dtos.outDTO.clubOutDTO;

import com.football.dtos.outDTO.playerOutDTO.PlayerListOutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubPlayersOutDTO {

    private Long clubId;
    private String clubName;

    private List<PlayerListOutDTO> players;
}
