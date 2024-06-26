package com.football.projections.clubProjections;

public interface IClubProjection {

    Long getId();
    String getName();
    boolean getDebt();
    Integer getAssociateNumber();

    Long getCoachId();
    String getCoachName();
    String getCoachLastName();

    Long getAssociationId();
    String getAssociationName();
}
