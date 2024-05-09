package com.football.dtos.outDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachOutDTO {

    private Long id;
    private String name;
    private String lastName;
    private String nationality;
    private Integer age;
}
