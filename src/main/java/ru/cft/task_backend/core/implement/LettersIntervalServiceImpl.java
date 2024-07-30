package ru.cft.task_backend.core.implement;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cft.task_backend.api.dto.MinLettersIntervalResponse;
import ru.cft.task_backend.core.utils.IntervalProcessor;
import ru.cft.task_backend.core.utils.ListConverter;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.repositories.LettersIntervalRepository;
import ru.cft.task_backend.core.services.LettersIntervalService;
import ru.cft.task_backend.models.LettersIntervalEntity;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LettersIntervalServiceImpl implements LettersIntervalService {
    private final LettersIntervalRepository intervalRepository;

    @Override
    public void addNewIntervals(List<Object[]> request) {

        if (request == null || request.isEmpty()) {
            throw new BadRequestException("The intervals list must be not empty");
        }
        List<Character[]> list = ListConverter.convertToTypedList(request, Character.class);
        List<Character[]> collapsed = IntervalProcessor.collapseIntersections(list, Comparator.comparing(element -> element[0]));
        collapsed.forEach(this::saveCharArray);
        log.info("Letters saved in database successfully");
    }

    @Override
    public MinLettersIntervalResponse findMinInterval() {
        Optional<LettersIntervalEntity> interval = intervalRepository.findMinInterval();
        MinLettersIntervalResponse response = interval.map(
                        lettersIntervalEntity -> MinLettersIntervalResponse
                                .builder()
                                .start(lettersIntervalEntity.getStart())
                                .end(lettersIntervalEntity.getEnd())
                                .build()
                )
                .orElse(null);

        log.info("Founded interval: {}", response);
        return response;
    }

    private void saveCharArray(Character[] array) {
        LettersIntervalEntity saved = intervalRepository.save(
                LettersIntervalEntity
                        .builder()
                        .start(array[0])
                        .end(array[1])
                        .build()
        );

        log.info("Saved in DB: {}", saved);
    }
}
