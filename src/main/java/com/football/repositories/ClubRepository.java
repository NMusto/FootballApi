package com.football.repositories;

import com.football.entities.Club;
import com.football.projections.clubProjections.IClubProjection;
import com.football.projections.competitionProjection.ICompetitionProjection;
import com.football.projections.playerProjections.IPlayersList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    public Optional<Club> findById(Long clubId);

    public Optional<IClubProjection> findClubById(Long clubId);

    public Page<IClubProjection> findAllProjetedBy(Pageable pageable);

    @Query(value = "SELECT p.id AS playerId, p.name AS playerName, p.last_name AS playerLastName " +
            "FROM player p " +
            "WHERE p.club_id = :clubId", nativeQuery = true)
    public List<IPlayersList> findAllPlayers(@Param("clubId") Long clubId);

    @Modifying
    @Query(value = "update player " +
            "set club_id = null " +
            "where club_id = :clubId", nativeQuery = true)
    public void deleteClubIdInPlayers(@Param("clubId") Long clubId);

    @Query(value = "SELECT comp.* FROM competition comp " +
            "JOIN club_competition cc ON comp.id = cc.competition_id " +
            "WHERE cc.club_id = :clubId", nativeQuery = true)
    public List<ICompetitionProjection> findCompetitionsByClubId(@Param("clubId") Long clubId);

}
