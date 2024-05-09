package com.football.mappers;

import com.football.dtos.outDTO.CoachOutDTO;
import com.football.entities.Coach;
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
