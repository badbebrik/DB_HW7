package com.example.olimp.repository;

import com.example.olimp.entity.Olympics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OlympicsRepository extends JpaRepository<Olympics, String> {
}
