package com.football.projections.coachProjections;

public interface ICoachProjection {

    Long getId();
    String getName();
    String getLastName();
    String getNationality();
    Integer getAge();

    Long getClubId();
    String getClubName();
}
