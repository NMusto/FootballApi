package com.football.projections.playerProjections;

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
