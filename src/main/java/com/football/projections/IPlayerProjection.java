package com.football.projections;

public interface IPlayerProjection {

    Long getId();
    String getName();
    String getLastName();
    String getNationality();
    Integer getAge();
    String getPosition();
    Integer getNumber();

    Long getClubId();
    String getClubName();
}
