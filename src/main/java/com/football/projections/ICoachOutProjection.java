package com.football.projections;

import com.football.entities.Club;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public interface ICoachOutProjection {

    Long getId();
    String getName();
    String getLastName();
    String getNationality();
    Integer getAge();
    Club getClub();
}
