package ru.cft.task_backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.cft.task_backend.models.DigitsIntervalEntity;
import ru.cft.task_backend.models.LettersIntervalEntity;

import java.util.Optional;

public interface LettersIntervalRepository extends JpaRepository<LettersIntervalEntity, Integer> {
    String FIND_MIN_INTERVAL_QUERY = "SELECT d FROM DigitsIntervalEntity d " +
            "WHERE d.end = (SELECT MIN(d2.end) FROM DigitsIntervalEntity d2) " +
            "AND d.start = (SELECT MIN(d2.start) FROM DigitsIntervalEntity d2) " +
            "ORDER BY d " +
            "LIMIT 1";

    @Query(FIND_MIN_INTERVAL_QUERY)
    Optional<LettersIntervalEntity> findMinInterval();
}