package com.football.dtos.outDTO;

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
}
