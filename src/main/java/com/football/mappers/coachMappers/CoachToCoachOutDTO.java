package com.football.mappers.coachMappers;

import com.football.dtos.outDTO.coachOutDTO.CoachOutDTO;
import com.football.entities.Coach;
import com.football.mappers.IMapper;
import org.springframework.stereotype.Component;

@Component
public class CoachToCoachOutDTO implements IMapper<Coach, CoachOutDTO> {

    @Override
    public CoachOutDTO map(Coach coach) {
        CoachOutDTO coachOutDTO = new CoachOutDTO();
        coachOutDTO.setId(coach.getId());
        coachOutDTO.setName(coach.getName());
        coachOutDTO.setLastName(coach.getLastName());
        coachOutDTO.setNationality(coach.getNationality());
        coachOutDTO.setAge(coach.getAge());

        return coachOutDTO;
    }
}
