package com.football.repositories;

import com.football.entities.Club;
import com.football.projections.IClubOutProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    public Optional<IClubOutProjection> findClubById(Long clubId);

}
