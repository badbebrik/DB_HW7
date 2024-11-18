package com.example.olimp.repository;

import com.example.olimp.DTO.CountryMedalRatioDto;
import com.example.olimp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(
            value = """
                        SELECT 
                            c.name AS countryName,
                            COUNT(DISTINCT e.event_id) * 1.0 / c.population AS medalToPopulationRatio
                        FROM 
                            countries c
                        JOIN 
                            players p ON p.country_id = c.country_id
                        JOIN 
                            results r ON r.player_id = p.player_id
                        JOIN 
                            events e ON e.event_id = r.event_id
                        JOIN 
                            olympics o ON o.olympic_id = e.olympic_id
                        WHERE 
                            o.year = 2000
                            AND e.is_team_event = 1
                            AND r.medal IS NOT NULL
                        GROUP BY 
                            c.country_id, c.name, c.population
                        ORDER BY 
                            (COUNT(DISTINCT e.event_id) * 1.0 / c.population) ASC
                        LIMIT 5
                    """,
            nativeQuery = true
    )
    List<CountryMedalRatioDto> findTop5CountriesWithLowestMedalToPopulationRatio();
}
