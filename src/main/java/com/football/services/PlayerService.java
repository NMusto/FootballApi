package com.football.services;

import com.football.dtos.inDTO.PlayerInDTO;
import com.football.dtos.outDTO.PlayerOutDTO;
import com.football.entities.Player;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.playerMappers.PlayerInDTOToPlayer;
import com.football.mappers.playerMappers.PlayerProjectionToPlayerOutDTO;
import com.football.mappers.playerMappers.PlayerToPlayerOutDTO;
import com.football.projections.IPlayerProjection;
import com.football.repositories.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerInDTOToPlayer playerInDTOToPlayer;
    private final PlayerToPlayerOutDTO playerToPlayerOutDTO;
    private final PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO;


    public PlayerService(PlayerRepository playerRepository, PlayerInDTOToPlayer playerInDTOToPlayer,
                         PlayerToPlayerOutDTO playerToPlayerOutDTO,
                         PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO) {
        this.playerRepository = playerRepository;
        this.playerInDTOToPlayer = playerInDTOToPlayer;
        this.playerToPlayerOutDTO = playerToPlayerOutDTO;
        this.playerProjectionToPlayerOutDTO = playerProjectionToPlayerOutDTO;
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
