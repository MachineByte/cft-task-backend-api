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
    public void addNewIntervals(List<Object[]> request, IntervalDataType type) {
        if(type == IntervalDataType.digits){
            digitsIntervalService.addNewIntervals(request);
        } else if(type == IntervalDataType.letters){
            lettersIntervalService.addNewIntervals(request);
        } else {
            throw new BadRequestException("Kind is not supported");
        }
    }

    @Override
    public MinIntervalResponse findMinInterval(IntervalDataType type) {
        if(type == IntervalDataType.digits){
            return digitsIntervalService.findMinInterval();
        } else if(type == IntervalDataType.letters){
            return lettersIntervalService.findMinInterval();
        } else {
            throw new BadRequestException("Kind is not supported");
        }
    }
}
