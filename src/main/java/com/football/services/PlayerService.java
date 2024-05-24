package com.football.services;

import com.football.dtos.inDTO.PlayerInDTO;
import com.football.dtos.outDTO.playerOutDTO.PlayerOutDTO;
import com.football.entities.Club;
import com.football.entities.Player;
import com.football.exceptions.InfoExceptions;
import com.football.mappers.playerMappers.PlayerInDTOToPlayer;
import com.football.mappers.playerMappers.PlayerProjectionPageToPlayerOutDTOPage;
import com.football.mappers.playerMappers.PlayerProjectionToPlayerOutDTO;
import com.football.mappers.playerMappers.PlayerToPlayerOutDTO;
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
    private final ClubService clubService;


    public PlayerService(PlayerRepository playerRepository, PlayerInDTOToPlayer playerInDTOToPlayer,
                         PlayerToPlayerOutDTO playerToPlayerOutDTO,
                         PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO,
                         PlayerProjectionPageToPlayerOutDTOPage playerProjectionPageToPlayerOutDTOPage,
                         ClubService clubService) {
        this.playerRepository = playerRepository;
        this.playerInDTOToPlayer = playerInDTOToPlayer;
        this.playerToPlayerOutDTO = playerToPlayerOutDTO;
        this.playerProjectionToPlayerOutDTO = playerProjectionToPlayerOutDTO;
        this.playerProjectionPageToPlayerOutDTOPage = playerProjectionPageToPlayerOutDTOPage;
        this.clubService = clubService;
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

    public PlayerOutDTO updatePlayerById(Long playerId, PlayerInDTO playerInDTO) {
        Player player = this.findPlayer(playerId);

        player.setName(playerInDTO.getName());
        player.setLastName(playerInDTO.getLastName());
        player.setNationality(playerInDTO.getNationality());
        player.setAge(playerInDTO.getAge());
        player.setPosition(playerInDTO.getPosition());
        player.setNumber(playerInDTO.getNumber());
        playerRepository.save(player);

        PlayerOutDTO playerOutDTO = playerToPlayerOutDTO.map(player);
        return playerOutDTO;
    }

    public String addClub (Long playerId, Long clubId) {

        Player player = this.findPlayer(playerId);

        if (clubId == null) {
            player.setClub(null);
        }
        else {
            Club club = clubService.findClub(clubId);
            player.setClub(club);
        }
        playerRepository.save(player);
        return "Club id: " + clubId + " successfully added to Player id: " + playerId;
    }

    public String deletePlayerById(Long playerId) {
        this.findPlayer(playerId);

        playerRepository.deleteById(playerId);
        return "Player id: " + playerId + " was successfully deleted.";
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
