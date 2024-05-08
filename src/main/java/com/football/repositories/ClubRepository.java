package com.football.repositories;

import com.football.entities.Club;
import com.football.projections.IClubCoachProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    public Optional<IClubCoachProjection> findClubById(Long clubId);

    public Optional<Club> findById(Long clubId);

    public Page<IClubCoachProjection> findAllProjetedBy (Pageable pageable);

}
