package com.football.dtos.inDTO;

import com.football.entities.Club;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CoachInDTO {

    @NotBlank @Size(min = 2, max = 50)
    private String name;

    @NotBlank @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank @Size(min = 2, max = 50)
    private String nationality;

    @Min(18)
    private Integer age;


}
