package com.example.olimp.controller;

import com.example.olimp.DTO.CountryMedalRatioDto;
import com.example.olimp.entity.Country;
import com.example.olimp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/lowest-medal-population-ratio")
    public List<CountryMedalRatioDto> getLowestMedalToPopulationRatio() {
        return countryRepository.findTop5CountriesWithLowestMedalToPopulationRatio();
    }
}
