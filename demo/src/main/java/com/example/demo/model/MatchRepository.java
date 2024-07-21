package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MatchRepository extends JpaRepository <Match, Integer> {
    // native query
    @Query(value = "select count(*) as whole_game, avg(game_sot) as sot_percent, "
            + "avg(game_poss) as poss_percent, "
            + "avg(game_tac) as tac_percent "
            + "from game "
            + "where (game_home = ?1 or game_away = ?2) and year(game_date) = ?3", nativeQuery = true)
    List<MatchResultDto> matchResultByYear(String hometeam, String awayteam, String year);
}
