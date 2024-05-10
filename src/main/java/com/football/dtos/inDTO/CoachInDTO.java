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
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "lastName must consist only of alphabet letters.")
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "nationality must consist only of alphabet letters.")
    private String nationality;

    @NotNull
    @Min(18)
    private Integer age;
}
