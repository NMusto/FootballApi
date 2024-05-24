package com.football.mappers.playerMappers;


import com.football.dtos.outDTO.playerOutDTO.PlayerOutDTO;
import com.football.entities.Player;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayerToPlayerOutDTO implements IMapper<Player, PlayerOutDTO> {

    @Override
    public PlayerOutDTO map(Player player) {
        PlayerOutDTO playerOutDTO = new PlayerOutDTO();
        playerOutDTO.setId(player.getId());
        playerOutDTO.setName(player.getName());
        playerOutDTO.setLastName(player.getLastName());
        playerOutDTO.setNationality(player.getNationality());
        playerOutDTO.setAge(player.getAge());
        playerOutDTO.setPosition(player.getPosition());
        playerOutDTO.setNumber(player.getNumber());

        return playerOutDTO;
    }
}
