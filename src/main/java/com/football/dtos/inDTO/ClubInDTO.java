package com.football.dtos.inDTO;

import com.football.entities.Association;
import com.football.entities.Coach;
import com.football.entities.Competition;
import com.football.entities.Player;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubInDTO {

    @NotBlank
    private String name;

    @Valid
    private CoachInDTO coachInDTO;

}
