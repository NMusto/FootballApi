package com.football.dtos.inDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String lastName;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String nationality;

    @NotNull
    @Positive
    private Integer age;

    @NotNull @Positive
    private String position;

    @NotNull @Positive
    private Integer number;
}
