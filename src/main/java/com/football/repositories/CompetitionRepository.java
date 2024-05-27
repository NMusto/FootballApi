package com.football.repositories;

import com.football.entities.Competition;
import com.football.projections.competitionProjection.ICompetitionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    public Optional<Competition> findById(Long competitionId);

    public Optional<ICompetitionProjection> findCompetitionById(Long competitionId);

    public List<ICompetitionProjection> findAllProjectedBy();

}
