package com.football.repositories;

import com.football.entities.Player;
import com.football.projections.IPlayerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    public Optional<IPlayerProjection> findPlayerById(Long playerId);
}
