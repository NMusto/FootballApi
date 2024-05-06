package com.football.projections;

public interface ICoachClubProjection {

    Long getId();
    String getName();
    String getLastName();
    String getNationality();
    Integer getAge();

    Long getClubId();
    String getClubName();
}
