package ru.cft.task_backend.core.services;

import java.util.List;

public interface DigitsIntervalService {
    void addNewIntervals(List<int[]> request);
    List<int[]> collapseIntersections(List<int[]> list);
}
