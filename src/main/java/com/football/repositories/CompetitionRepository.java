package com.football.repositories;

import com.football.entities.Competition;
import com.football.projections.clubProjections.IClubsList;
import com.football.projections.competitionProjection.ICompetitionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    public Optional<Competition> findById(Long competitionId);

    public Optional<ICompetitionProjection> findCompetitionById(Long competitionId);

    public List<ICompetitionProjection> findAllProjectedBy();

    @Query(value = "SELECT c.id AS clubId, c.name AS clubName " +
            "FROM club c " +
            "INNER JOIN club_competition cc ON c.id = cc.club_id " +
            "WHERE cc.competition_id = :competitionId", nativeQuery = true)
    public List<IClubsList> findAllClubsByCompetitionId(@Param("competitionId") Long competitionId);


    @Modifying
    @Query(value = "DELETE FROM club_competition " +
            "WHERE club_id = :clubId AND competition_id = :competitionId", nativeQuery = true)
    public void deleteClubFromCompetition(@Param("competitionId") Long competitionId, @Param("clubId") Long clubId);
}
