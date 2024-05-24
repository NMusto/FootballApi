package com.football.services;

import com.football.dtos.inDTO.PlayerInDTO;
import com.football.dtos.outDTO.ClubOutDTO;
import com.football.dtos.outDTO.PlayerOutDTO;
import com.football.entities.Player;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.playerMappers.PlayerInDTOToPlayer;
import com.football.mappers.playerMappers.PlayerProjectionPageToPlayerOutDTOPage;
import com.football.mappers.playerMappers.PlayerProjectionToPlayerOutDTO;
import com.football.mappers.playerMappers.PlayerToPlayerOutDTO;
import com.football.projections.IClubProjection;
import com.football.projections.IPlayerProjection;
import com.football.repositories.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerInDTOToPlayer playerInDTOToPlayer;
    private final PlayerToPlayerOutDTO playerToPlayerOutDTO;
    private final PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO;
    private final PlayerProjectionPageToPlayerOutDTOPage playerProjectionPageToPlayerOutDTOPage;


    public PlayerService(PlayerRepository playerRepository, PlayerInDTOToPlayer playerInDTOToPlayer,
                         PlayerToPlayerOutDTO playerToPlayerOutDTO,
                         PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO,
                         PlayerProjectionPageToPlayerOutDTOPage playerProjectionPageToPlayerOutDTOPage) {
        this.playerRepository = playerRepository;
        this.playerInDTOToPlayer = playerInDTOToPlayer;
        this.playerToPlayerOutDTO = playerToPlayerOutDTO;
        this.playerProjectionToPlayerOutDTO = playerProjectionToPlayerOutDTO;
        this.playerProjectionPageToPlayerOutDTOPage = playerProjectionPageToPlayerOutDTOPage;
    }


    public PlayerOutDTO createPlayer(PlayerInDTO playerInDTO) {
        Player player = playerInDTOToPlayer.map(playerInDTO);
        playerRepository.save(player);
        PlayerOutDTO playerOutDTO = playerToPlayerOutDTO.map(player);
        return playerOutDTO;
    }

    public PlayerOutDTO findPlayerById(Long playerId) {
        IPlayerProjection iPlayerProjection = this.findPlayerWithProjection(playerId);
        PlayerOutDTO playerOutDTO = playerProjectionToPlayerOutDTO.map(iPlayerProjection);
        return playerOutDTO;
    }

    public Page<PlayerOutDTO> findAllPlayers(Pageable pageable) {
        Page<IPlayerProjection> playerProjections = playerRepository.findAllProjetedBy(pageable);
        if (playerProjections.isEmpty()) {
            throw new InfoExceptions( "There are currently no registered Players.", HttpStatus.NOT_FOUND);
        }
        return playerProjectionPageToPlayerOutDTOPage.map(playerProjections);
    }



    /*------------------------------------------------------------------------------------------------*/
    /*                                   PLAYER: UTIlS                                                */
    /*------------------------------------------------------------------------------------------------*/


    // It is not an endpoint, it returns a Player Entity

    public Player findPlayer (Long playerId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if(optionalPlayer.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalPlayer.get();
    }

    //finds and validates Player entity exists and returns IPlayerProjection

    public IPlayerProjection findPlayerWithProjection (Long playerId) {
        Optional<IPlayerProjection> optionalPlayer = playerRepository.findPlayerById(playerId);
        if(optionalPlayer.isEmpty()) {
            throw new InfoExceptions("Id does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalPlayer.get();
    }

}
