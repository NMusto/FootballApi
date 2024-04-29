package com.football.projections;

import com.football.entities.Association;
import com.football.entities.Coach;

public interface IClubOutProjection {

    Long getId();
    String getName();
    boolean getDebt();
    Association getAssociation();
    Integer getAssociationNumber();
    Coach getCoach();
}
