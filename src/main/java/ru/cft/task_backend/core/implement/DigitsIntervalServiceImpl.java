package ru.cft.task_backend.core.implement;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.core.utils.IntervalProcessor;
import ru.cft.task_backend.core.utils.ListConverter;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.exceptions.BadStateException;
import ru.cft.task_backend.core.repositories.DigitsIntervalRepository;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.*;

/**
 * Service implementation for managing digit intervals.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class DigitsIntervalServiceImpl implements DigitsIntervalService {
    private final DigitsIntervalRepository intervalRepository;

    @Override
    public void addNewIntervals(List<Object[]> request) {

        if (request == null || request.isEmpty()) {
            throw new BadRequestException("The intervals list must be not empty");
        }
        List<Integer[]> list = ListConverter.convertToTypedList(request, Integer.class);
        List<Integer[]> collapsed = IntervalProcessor.collapseIntersections(list, Comparator.comparingInt(element -> element[0]));

        collapsed.forEach(this::saveIntArray);
        log.info("Digits saved in database successfully");
    }


    @Override
    public MinDigitsIntervalResponse findMinInterval() {
        Optional<DigitsIntervalEntity> interval = intervalRepository.findMinInterval();
        MinDigitsIntervalResponse response = interval.map(
                        digitsIntervalEntity -> MinDigitsIntervalResponse
                                .builder()
                                .start(digitsIntervalEntity.getStart())
                                .end(digitsIntervalEntity.getEnd())
                                .build()
                )
                .orElse(null);
        log.info("Founded interval: {}", response);
        return response;
    }

    private void saveIntArray(Integer[] array) {
        DigitsIntervalEntity saved = intervalRepository.save(
                DigitsIntervalEntity
                        .builder()
                        .start(array[0])
                        .end(array[1])
                        .build()
        );

        log.info("Saved in DB: {}", saved);
    }
}
