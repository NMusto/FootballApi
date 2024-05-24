package com.football.dtos.outDTO.playerOutDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerListOutDTO {

    private Long playerId;
    private String playerName;
    private String playerLastName;
}
