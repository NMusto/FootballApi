package com.football.projections.competitionProjection;

import java.time.LocalDate;

public interface ICompetitionProjection {

    Long getId();
    String getName();
    Integer getQuantityPrice();
    LocalDate getStartDate();
    LocalDate getEndDate();
}
