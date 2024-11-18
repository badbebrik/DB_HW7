package com.example.olimp.controller;

import com.example.olimp.repository.ResultRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OlympicController {

    private final ResultRepository resultRepository;

    public OlympicController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping("/olympics/2004/gold-medals")
    public List<Object[]> getGoldMedals2004() {
        return resultRepository.findBirthYearPlayerCountAndGoldMedalsByOlympicYear(2004);
    }
}
