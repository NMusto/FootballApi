package com.football.mappers.playerMappers;

import com.football.dtos.outDTO.playerOutDTO.PlayerOutDTO;
import com.football.mappers.IMapper;
import com.football.projections.IPlayerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerProjectionPageToPlayerOutDTOPage implements IMapper<Page<IPlayerProjection>, Page<PlayerOutDTO> > {

    private final PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO;

    public PlayerProjectionPageToPlayerOutDTOPage(PlayerProjectionToPlayerOutDTO playerProjectionToPlayerOutDTO) {
        this.playerProjectionToPlayerOutDTO = playerProjectionToPlayerOutDTO;
    }


    @Override
    public Page<PlayerOutDTO> map(Page<IPlayerProjection> playerProjections) {

        List<PlayerOutDTO> playerOutDTOList = playerProjections.stream()
                .map(playerProjection -> playerProjectionToPlayerOutDTO.map(playerProjection))
                .collect(Collectors.toList());

        return new PageImpl<>(playerOutDTOList, playerProjections.getPageable(), playerProjections.getTotalElements());
    }
}
