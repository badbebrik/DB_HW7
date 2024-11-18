package com.example.olimp;

import com.example.olimp.entity.*;
import com.example.olimp.repository.*;
import net.datafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Seeder {

    private final CountryRepository countryRepository;
    private final OlympicsRepository olympicsRepository;
    private final PlayerRepository playerRepository;
    private final EventRepository eventRepository;
    private final ResultRepository resultRepository;

    private final Faker faker = new Faker();

    @Transactional
    public void seedCountries(int numCountries) {
        List<Country> countries = new ArrayList<>();
        Set<String> existingIds = new HashSet<>(countryRepository.findAll().stream().map(Country::getCountryId).collect(Collectors.toSet()));
        for (int i = 0; i < numCountries; i++) {
            String countryId = generateUniqueId(3, existingIds);
            existingIds.add(countryId);
            Country country = Country.builder()
                    .countryId(countryId)
                    .name(faker.country().name())
                    .areaSqkm(faker.number().numberBetween(1000, 10_000_000))
                    .population(faker.number().numberBetween(100_000, 1_500_000_000))
                    .build();
            countries.add(country);
        }
        countryRepository.saveAll(countries);
        System.out.println(numCountries + " countries seeded.");
    }

    @Transactional
    public void seedOlympics(int numOlympics) {
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new IllegalStateException("No countries available. Seed countries first.");
        }

        List<Olympics> olympicsList = new ArrayList<>();
        Set<String> existingIds = new HashSet<>(olympicsRepository.findAll().stream().map(Olympics::getOlympicId).collect(Collectors.toSet()));
        for (int i = 0; i < numOlympics; i++) {
            Country country = countries.get(faker.number().numberBetween(0, countries.size()));
            int year = faker.number().numberBetween(1896, 2024);
            LocalDate startDate = LocalDate.of(year, faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28));
            LocalDate endDate = startDate.plusDays(faker.number().numberBetween(10, 20));
            String olympicId = generateUniqueId(7, existingIds);
            existingIds.add(olympicId);
            Olympics olympic = Olympics.builder()
                    .olympicId(olympicId)
                    .country(country)
                    .city(faker.address().city())
                    .year(year)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            olympicsList.add(olympic);
        }
        olympicsRepository.saveAll(olympicsList);
        System.out.println(numOlympics + " olympics seeded.");
    }

    @Transactional
    public void seedPlayers(int numPlayers) {
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new IllegalStateException("No countries available. Seed countries first.");
        }

        List<Player> players = new ArrayList<>();
        Set<String> existingIds = new HashSet<>(playerRepository.findAll().stream().map(Player::getPlayerId).collect(Collectors.toSet()));
        for (int i = 0; i < numPlayers; i++) {
            Country country = countries.get(faker.number().numberBetween(0, countries.size()));
            LocalDate birthdate = LocalDate.of(faker.number().numberBetween(1950, 2005),
                    faker.number().numberBetween(1, 12),
                    faker.number().numberBetween(1, 28));
            String playerId = generateUniqueId(10, existingIds);
            existingIds.add(playerId);
            Player player = Player.builder()
                    .playerId(playerId)
                    .name(faker.name().fullName())
                    .country(country)
                    .birthdate(birthdate)
                    .build();
            players.add(player);
        }
        playerRepository.saveAll(players);
        System.out.println(numPlayers + " players seeded.");
    }

    @Transactional
    public void seedEvents(int numEvents) {
        List<Olympics> olympicsList = olympicsRepository.findAll();
        if (olympicsList.isEmpty()) {
            throw new IllegalStateException("No olympics available. Seed olympics first.");
        }

        List<Event> events = new ArrayList<>();
        Set<String> existingIds = new HashSet<>(eventRepository.findAll().stream().map(Event::getEventId).collect(Collectors.toSet()));
        for (int i = 0; i < numEvents; i++) {
            Olympics olympic = olympicsList.get(faker.number().numberBetween(0, olympicsList.size()));
            boolean isTeam = faker.bool().bool();
            String eventId = generateUniqueId(7, existingIds);
            existingIds.add(eventId);
            Event event = Event.builder()
                    .eventId(eventId)
                    .name(faker.olympicSport().summerOlympics())
                    .eventType(isTeam ? "Team" : "Individual")
                    .olympics(olympic)
                    .isTeamEvent(isTeam ? 1 : 0)
                    .numPlayersInTeam(isTeam ? faker.number().numberBetween(2, 20) : null)
                    .resultNotedIn("Points/Seconds")
                    .build();
            events.add(event);
        }
        eventRepository.saveAll(events);
        System.out.println(numEvents + " events seeded.");
    }

    @Transactional
    public void seedResults(int numResults) {
        List<Event> events = eventRepository.findAll();
        List<Player> players = playerRepository.findAll();
        if (events.isEmpty()) {
            throw new IllegalStateException("No events available. Seed events first.");
        }
        if (players.isEmpty()) {
            throw new IllegalStateException("No players available. Seed players first.");
        }

        List<Result> results = new ArrayList<>();
        Set<ResultId> existingIds = new HashSet<>(resultRepository.findAll().stream().map(Result::getId).collect(Collectors.toSet()));
        for (int i = 0; i < numResults; i++) {
            Event event = events.get(faker.number().numberBetween(0, events.size()));
            Player player = players.get(faker.number().numberBetween(0, players.size()));
            String medal = faker.options().option("GOLD", "SILVER", "BRONZE", null);
            Double resultValue = faker.number().randomDouble(2, 10, 1000);
            ResultId resultId = ResultId.builder()
                    .eventId(event.getEventId())
                    .playerId(player.getPlayerId())
                    .build();
            if (existingIds.contains(resultId)) {
                continue;
            }
            existingIds.add(resultId);
            Result result = Result.builder()
                    .id(resultId)
                    .event(event)
                    .player(player)
                    .medal(medal)
                    .result(resultValue)
                    .build();
            results.add(result);
        }
        resultRepository.saveAll(results);
        System.out.println(results.size() + " results seeded.");
    }

    private String generateUniqueId(int length, Set<String> existingIds) {
        String id;
        do {
            id = faker.lorem().characters(length).toUpperCase();
        } while (existingIds.contains(id));
        return id;
    }
}

