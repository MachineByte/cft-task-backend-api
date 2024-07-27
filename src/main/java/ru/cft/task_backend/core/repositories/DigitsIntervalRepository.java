package ru.cft.task_backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Query;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.Optional;

public interface DigitsIntervalRepository extends JpaRepository<DigitsIntervalEntity, Integer> {
    String FIND_MIN_INTERVAL_NATIVE_QUERY = "SELECT  * FROM DIGITS_INTERVAL_ENTITY  " +
            "WHERE START_VALUE = (SELECT MIN(START_VALUE) FROM DIGITS_INTERVAL_ENTITY ) " +
            "AND END_VALUE = (SELECT MIN(END_VALUE) FROM DIGITS_INTERVAL_ENTITY ) " +
            "LIMIT 1";

    @Query(nativeQuery = true, value = FIND_MIN_INTERVAL_NATIVE_QUERY)
    Optional<DigitsIntervalEntity> findMinInterval();
}
