package com.football.projections;

public interface IClubProjection {

    Long getId();
    String getName();
    boolean getDebt();
    Integer getAssociationNumber();

    Long getCoachId();
    String getCoachName();
    String getCoachLastName();
}