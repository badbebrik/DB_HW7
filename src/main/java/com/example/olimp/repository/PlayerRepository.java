package com.example.olimp.repository;

import com.example.olimp.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    @Query("SELECT DISTINCT p.name, o.olympicId " +
            "FROM Result r " +
            "JOIN r.player p " +
            "JOIN r.event e " +
            "JOIN e.olympics o " +
            "WHERE r.medal IN ('GOLD', 'SILVER', 'BRONZE')")
    List<Object[]> findPlayersWithAtLeastOneMedal();

    @Query("SELECT p.country.name, " +
            "COUNT(CASE WHEN UPPER(SUBSTRING(p.name, 1, 1)) IN ('A', 'E', 'I', 'O', 'U') THEN 1 END) * 100.0 / COUNT(p.playerId) AS vowelPercentage " +
            "FROM Player p " +
            "GROUP BY p.country.name " +
            "ORDER BY vowelPercentage DESC")
    List<Object[]> findCountryWithHighestVowelNamePercentage();
}
