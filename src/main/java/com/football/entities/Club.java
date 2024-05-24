package com.football.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean debt;

    @Column(name = "associate_number")
    private Integer associateNumber;

    @OneToOne(targetEntity = Coach.class)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(targetEntity = Association.class)
    private Association association;

    @OneToMany(targetEntity = Player.class, fetch = FetchType.LAZY, mappedBy = "club")
    private List<Player> players;

    @ManyToMany(targetEntity = Competition.class, fetch = FetchType.LAZY)
    @JoinTable(name = "club_competition", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name = "competition_id"))
    private List<Competition> competitions;


}
