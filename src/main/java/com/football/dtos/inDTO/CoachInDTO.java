package com.football.dtos.inDTO;


import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachInDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "debe estar formado solo por letras")
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "debe estar formado solo por letras")
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "debe estar formado solo por letras")
    private String nationality;

    @NotNull
    @Min(18)
    private Integer age;
}
