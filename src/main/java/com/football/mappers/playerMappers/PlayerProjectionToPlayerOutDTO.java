package com.football.mappers.playerMappers;

import com.football.dtos.outDTO.playerOutDTO.PlayerOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.IPlayerProjection;
import org.springframework.stereotype.Component;

@Component
public class PlayerProjectionToPlayerOutDTO implements IMapper<IPlayerProjection, PlayerOutDTO> {

    @Override
    public PlayerOutDTO map(IPlayerProjection playerProjection) {
        PlayerOutDTO playerOutDTO = new PlayerOutDTO();

        playerOutDTO.setId(playerProjection.getId());
        playerOutDTO.setName(playerProjection.getName());
        playerOutDTO.setLastName(playerProjection.getLastName());
        playerOutDTO.setNationality(playerProjection.getNationality());
        playerOutDTO.setAge(playerProjection.getAge());
        playerOutDTO.setPosition(playerProjection.getPosition());
        playerOutDTO.setNumber(playerProjection.getNumber());
        playerOutDTO.setClubId(playerProjection.getClubId());
        playerOutDTO.setClubName(playerProjection.getClubName());

        return playerOutDTO;
    }
}

