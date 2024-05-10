package com.football.dtos.inDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubInDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String name;

    @NotNull @Positive
    private Integer associationNumber;

}
