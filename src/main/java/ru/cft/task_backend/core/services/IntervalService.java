package ru.cft.task_backend.core.services;

import org.springframework.stereotype.Service;
import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.api.dto.MinIntervalResponse;

import java.util.List;

public interface IntervalService {
    void addNewIntervals(List<Object[]> request);
    <T> List<T[]> collapseIntersections(List<T[]> list);
    MinIntervalResponse findMinInterval();
}
