package com.football.repositories;

import com.football.entities.Player;
import com.football.projections.IPlayerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    public Optional<Player> findById(Long playerId);

    public Optional<IPlayerProjection> findPlayerById(Long playerId);

    public Page<IPlayerProjection> findAllProjetedBy(Pageable pageable);
}
