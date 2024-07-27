package ru.cft.task_backend.core.implement;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.core.converters.ListConverter;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.exceptions.BadStateException;
import ru.cft.task_backend.core.repositories.DigitsIntervalRepository;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.*;

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
        List<Integer[]> collapsed = collapseIntersections(list);

        collapsed.forEach(this::saveIntArray);
        log.info("Digits saved in database successfully");
    }

    @Override
    public List<Integer[]> collapseIntersections(List<Integer[]> list) throws BadRequestException{
        List<Integer[]> result = new ArrayList<>();

        list = list.stream().sorted(Comparator.comparingInt(arr -> arr[0])) .toList();

        Integer[] current = list.getFirst();

        if(current.length != 2){
            throw new BadStateException("Incorrect interval");
        }

        for (int i = 1; i < list.size(); i++) {
            Integer[] next = list.get(i);

            if (next.length != 2) {
                throw new BadStateException("Incorrect interval");
            }
            if (current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                result.add(current);
                current = next;
            }
        }
        result.add(current);

        return result;
    }

    @Override
    public MinDigitsIntervalResponse findMinInterval() {
        Optional<DigitsIntervalEntity> interval = intervalRepository.findMinInterval();
        return interval.map(
                digitsIntervalEntity -> MinDigitsIntervalResponse
                    .builder()
                    .start(digitsIntervalEntity.getStart())
                    .end(digitsIntervalEntity.getEnd())
                    .build()
                )
                .orElse(null);
    }

    private void saveIntArray(Integer[] array) {
        intervalRepository.save(
                DigitsIntervalEntity
                        .builder()
                        .start(array[0])
                        .end(array[1])
                        .build()
        );


    }
}
