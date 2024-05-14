package com.football.dtos.inDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationInDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must consist only of alphabet letters.")
    private String president;
}
