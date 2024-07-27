package ru.cft.task_backend.core.services;

import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.List;
import java.util.Optional;

public interface DigitsIntervalService {
    void addNewIntervals(List<Object[]> request);
    MinDigitsIntervalResponse findMinInterval();
}
