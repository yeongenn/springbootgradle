package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="game")
public class Match {

    @Id
    @Column(name="game_code")
    private int code;

    @Column(name="game_home")
    private String hometeam;

    @Column(name="game_away")
    private String awayteam;

    @Column(name="game_poss")
    private int possession;

    @Column(name="game_sot")
    private int shootsOnTarget;

    @Column(name="game_tac")
    private int tackle;
}
