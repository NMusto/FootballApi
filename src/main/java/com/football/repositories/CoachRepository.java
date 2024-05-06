package com.football.repositories;

import com.football.entities.Coach;
import com.football.projections.ICoachClubProjection;
import com.football.projections.ICoachOutProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    public Optional<ICoachClubProjection> findCoachById(Long coachId);
}
