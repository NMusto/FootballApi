package com.football.dtos.inDTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionInDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String name;

    @NotNull
    @Positive
    private Integer quantityPrice;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;
}
