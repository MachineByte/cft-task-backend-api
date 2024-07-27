package ru.cft.task_backend.core.services;

import ru.cft.task_backend.api.dto.MinIntervalResponse;
import ru.cft.task_backend.models.enums.IntervalDataType;

import java.util.List;

public interface IntervalDispatchService {
    void addNewIntervals(List<Object[]> request, IntervalDataType type);
    MinIntervalResponse findMinInterval(IntervalDataType type);
}
