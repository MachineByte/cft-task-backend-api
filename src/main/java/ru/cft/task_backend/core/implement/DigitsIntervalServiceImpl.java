package ru.cft.task_backend.core.implement;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.repositories.DigitsIntervalRepository;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitsIntervalServiceImpl implements DigitsIntervalService {
    private final DigitsIntervalRepository intervalRepository;

    @Override
    public void addNewIntervals(List<int[]> request) {

        if (request == null || request.isEmpty()) {
            throw new BadRequestException("The intervals list must be not empty");
        }
        List<int[]> list = request.stream().toList();
        List<int[]> collapsed = collapseIntersections(list);

        collapsed.forEach(this::saveIntArray);
    }

    @Override
    public List<int[]> collapseIntersections(List<int[]> list) throws BadRequestException{
        List<int[]> result = new ArrayList<>();

        list = list
                .stream()
                .sorted(
                        Comparator.comparingInt(arr -> arr[0])
                )
                .toList();

        int[] current = list.getFirst();

        if(current.length != 2){
            throw new BadRequestException("Incorrect interval");
        }

        for (int i = 1; i < list.size(); i++) {
            int[] next = list.get(i);

            if (next.length != 2 || current.length != 2) {
                throw new BadRequestException("Incorrect interval");
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

    private void saveIntArray(int[] array) {
        intervalRepository.save(
                DigitsIntervalEntity
                        .builder()
                        .start(array[0])
                        .end(array[1])
                        .build()
        );
    }
}
