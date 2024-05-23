package com.football.mappers.playerMappers;

import com.football.dtos.inDTO.PlayerInDTO;
import com.football.entities.Player;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayerInDTOToPlayer implements IMapper<PlayerInDTO, Player> {
    @Override
    public Player map(PlayerInDTO playerInDTO) {
        Player player = Player.builder()
                .name(playerInDTO.getName())
                .lastName(playerInDTO.getLastName())
                .nationality(playerInDTO.getNationality())
                .age(playerInDTO.getAge())
                .position(playerInDTO.getPosition())
                .number(playerInDTO.getNumber())
                .build();

        return player;
    }
}
