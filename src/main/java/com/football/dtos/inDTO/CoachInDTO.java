package com.football.dtos.inDTO;


import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachInDTO {

    @NotBlank @Size(min = 2, max = 50)
    private String name;

    @NotBlank @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank @Size(min = 2, max = 50)
    private String nationality;

    @Min(18)
    private Integer age;

    private Long clubId;






}
