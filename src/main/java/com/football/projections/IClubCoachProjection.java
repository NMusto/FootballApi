package com.football.projections;

import com.football.entities.Association;

public interface IClubCoachProjection {

    Long getId();
    String getName();
    boolean getDebt();
    Association getAssociation();
    Integer getAssociationNumber();

    Long getCoachId();
    String getCoachName();
    String getCoachLastName();
}
