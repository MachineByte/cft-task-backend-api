package ru.cft.task_backend.core.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cft.task_backend.api.dto.MinIntervalResponse;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.core.services.IntervalDispatchService;
import ru.cft.task_backend.core.services.LettersIntervalService;
import ru.cft.task_backend.models.enums.IntervalDataType;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntervalDispatchServiceImpl implements IntervalDispatchService {
    private final DigitsIntervalService digitsIntervalService;
    private final LettersIntervalService lettersIntervalService;

    @Override
    public void addNewIntervals(List<Object[]> request, String type) {

        IntervalDataType dataType = getIntervalDataType(type);

        switch (dataType){
            case DIGITS -> digitsIntervalService.addNewIntervals(request);
            case LETTERS -> lettersIntervalService.addNewIntervals(request);
        }
    }

    @Override
    public MinIntervalResponse findMinInterval(String type) {
        IntervalDataType dataType = getIntervalDataType(type);

        return switch (dataType){
            case DIGITS -> digitsIntervalService.findMinInterval();
            case LETTERS -> lettersIntervalService.findMinInterval();
        };

    }

    private IntervalDataType getIntervalDataType(String type) {
        IntervalDataType dataType;
        try {
            dataType = IntervalDataType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException  e){
            throw new BadRequestException("Kind " + type + " is not supported");
        }
        return dataType;
    }
}
