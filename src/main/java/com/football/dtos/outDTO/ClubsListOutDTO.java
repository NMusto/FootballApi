package com.football.dtos.outDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubsListOutDTO {

    private Long clubId;
    private String clubName;
}
