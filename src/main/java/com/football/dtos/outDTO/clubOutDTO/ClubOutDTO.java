package com.football.dtos.outDTO.clubOutDTO;

import com.football.entities.Association;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubOutDTO {

    private Long id;
    private String name;
    private boolean debt;
    private Integer associateNumber;

    private Long coachId;
    private String coachName;
    private String coachLastName;

    private Long associationId;
    private String associationName;
}
