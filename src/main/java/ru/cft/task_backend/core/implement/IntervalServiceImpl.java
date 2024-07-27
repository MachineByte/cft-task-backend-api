package ru.cft.task_backend.core.implement;

import ru.cft.task_backend.api.dto.MinIntervalResponse;
import ru.cft.task_backend.core.services.IntervalService;

import java.util.List;

public class IntervalServiceImpl implements IntervalService {
    @Override
    public void addNewIntervals(List<Object[]> request) {

    }

    @Override
    public <T> List<T[]> collapseIntersections(List<T[]> list) {
        return List.of();
    }

    @Override
    public MinIntervalResponse findMinInterval() {
        return null;
    }
}
