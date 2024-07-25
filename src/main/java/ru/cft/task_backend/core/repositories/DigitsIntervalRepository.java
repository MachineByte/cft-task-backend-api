package ru.cft.task_backend.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.task_backend.models.DigitsIntervalEntity;

public interface DigitsIntervalRepository extends JpaRepository<DigitsIntervalEntity, Integer> {
}