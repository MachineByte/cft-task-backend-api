package ru.cft.task_backend.core.implement;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cft.task_backend.api.dto.MinLettersIntervalResponse;
import ru.cft.task_backend.core.converters.ListConverter;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.exceptions.BadStateException;
import ru.cft.task_backend.core.repositories.LettersIntervalRepository;
import ru.cft.task_backend.core.services.LettersIntervalService;
import ru.cft.task_backend.models.LettersIntervalEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        List<Character[]> collapsed = collapseIntersections(list);

        collapsed.forEach(this::saveIntArray);
        log.info("Letters saved in database successfully");
    }

    @Override
    public List<Character[]> collapseIntersections(List<Character[]> list) throws BadRequestException{
        List<Character[]> result = new ArrayList<>();

        list = list.stream().sorted(Comparator.comparingInt(arr -> arr[0])) .toList();

        Character[] current = list.getFirst();

        if(current.length != 2){
            throw new BadStateException("Incorrect interval");
        }

        for (int i = 1; i < list.size(); i++) {
            Character[] next = list.get(i);

            if (next.length != 2) {
                throw new BadStateException("Incorrect interval");
            }
            if (current[1] >= next[0]) {
                current[1] = (char) Math.max(current[1], next[1]);
            } else {
                result.add(current);
                current = next;
            }
        }
        result.add(current);

        return result;
    }

    @Override
    public MinLettersIntervalResponse findMinInterval() {
        Optional<LettersIntervalEntity> interval = intervalRepository.findMinInterval();
        return interval.map(
                lettersIntervalEntity -> MinLettersIntervalResponse
                    .builder()
                    .start(lettersIntervalEntity.getStart())
                    .end(lettersIntervalEntity.getEnd())
                    .build()
                )
                .orElse(null);
    }

    private void saveIntArray(Character[] array) {
        intervalRepository.save(
                LettersIntervalEntity
                        .builder()
                        .start(array[0])
                        .end(array[1])
                        .build()
        );
    }
}
