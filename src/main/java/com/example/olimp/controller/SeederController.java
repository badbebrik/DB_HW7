package com.example.olimp.controller;

import com.example.olimp.Seeder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seeder")
@RequiredArgsConstructor
public class SeederController {

    private final Seeder seeder;

    @PostMapping("/countries")
    public ResponseEntity<String> seedCountries(@RequestParam(defaultValue = "10") int num) {
        try {
            seeder.seedCountries(num);
            return ResponseEntity.ok(num + " стран успешно добавлено.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при добавлении стран: " + e.getMessage());
        }
    }


    @PostMapping("/olympics")
    public ResponseEntity<String> seedOlympics(@RequestParam(defaultValue = "5") int num) {
        try {
            seeder.seedOlympics(num);
            return ResponseEntity.ok(num + " Олимпийских игр успешно добавлено.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при добавлении Олимпийских игр: " + e.getMessage());
        }
    }

    @PostMapping("/players")
    public ResponseEntity<String> seedPlayers(@RequestParam(defaultValue = "50") int num) {
        try {
            seeder.seedPlayers(num);
            return ResponseEntity.ok(num + " игроков успешно добавлено.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при добавлении игроков: " + e.getMessage());
        }
    }

    @PostMapping("/events")
    public ResponseEntity<String> seedEvents(@RequestParam(defaultValue = "20") int num) {
        try {
            seeder.seedEvents(num);
            return ResponseEntity.ok(num + " событий успешно добавлено.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при добавлении событий: " + e.getMessage());
        }
    }


    @PostMapping("/results")
    public ResponseEntity<String> seedResults(@RequestParam(defaultValue = "100") int num) {
        try {
            seeder.seedResults(num);
            return ResponseEntity.ok(num + " результатов успешно добавлено.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при добавлении результатов: " + e.getMessage());
        }
    }


    @PostMapping("/all")
    public ResponseEntity<String> seedAll(
            @RequestParam(defaultValue = "10") int countries,
            @RequestParam(defaultValue = "5") int olympics,
            @RequestParam(defaultValue = "50") int players,
            @RequestParam(defaultValue = "20") int events,
            @RequestParam(defaultValue = "100") int results) {
        try {
            seeder.seedCountries(countries);
            seeder.seedOlympics(olympics);
            seeder.seedPlayers(players);
            seeder.seedEvents(events);
            seeder.seedResults(results);
            return ResponseEntity.ok("Успех");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при сидировании данных: " + e.getMessage());
        }
    }
}

