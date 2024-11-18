package com.example.olimp.controller;

import com.example.olimp.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players/medals")
    public List<Object[]> getPlayersWithMedals() {
        return playerRepository.findPlayersWithAtLeastOneMedal();
    }

    @GetMapping("/countries/vowel-percentage")
    public List<Object[]> getCountriesVowelPercentage() {
        return playerRepository.findCountryWithHighestVowelNamePercentage();
    }
}