package com.example.olimp.repository;

import com.example.olimp.entity.Result;
import com.example.olimp.entity.ResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ResultRepository extends JpaRepository<Result, ResultId> {

    @Query("SELECT YEAR(p.birthdate) AS birthYear, COUNT(DISTINCT p.playerId) AS playerCount, COUNT(r.medal) AS goldMedals " +
            "FROM Result r " +
            "JOIN r.player p " +
            "JOIN r.event e " +
            "JOIN e.olympics o " +
            "WHERE o.year = :year AND r.medal = 'GOLD' " +
            "GROUP BY YEAR(p.birthdate) " +
            "ORDER BY YEAR(p.birthdate)")
    List<Object[]> findBirthYearPlayerCountAndGoldMedalsByOlympicYear(int year);
}
