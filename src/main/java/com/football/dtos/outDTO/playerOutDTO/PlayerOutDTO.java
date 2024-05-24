package com.football.dtos.outDTO.playerOutDTO;

import com.football.entities.Club;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerOutDTO {

    private Long id;
    private String name;
    private String lastName;
    private String nationality;
    private Integer age;
    private String position;
    private Integer number;

    private Long clubId;
    private String clubName;


}
