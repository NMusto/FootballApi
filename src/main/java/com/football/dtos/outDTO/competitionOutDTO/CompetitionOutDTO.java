package com.football.dtos.outDTO.competitionOutDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionOutDTO {

    private Long id;
    private String name;
    private Integer quantityPrice;
    private LocalDate startDate;
    private LocalDate endDate;

}
