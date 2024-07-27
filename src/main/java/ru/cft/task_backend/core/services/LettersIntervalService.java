package ru.cft.task_backend.core.services;

import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.api.dto.MinLettersIntervalResponse;

import java.util.List;
import java.util.Objects;

public interface LettersIntervalService {
    void addNewIntervals(List<Object[]> request);
    List<Character[]> collapseIntersections(List<Character[]> list);
    MinLettersIntervalResponse findMinInterval();
}
