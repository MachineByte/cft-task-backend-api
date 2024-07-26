package ru.cft.task_backend.core.services;

import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.List;
import java.util.Optional;

public interface DigitsIntervalService {
    void addNewIntervals(List<int[]> request);
    List<int[]> collapseIntersections(List<int[]> list);
    MinDigitsIntervalResponse findMinInterval();
}
