package ru.cft.task_backend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.core.services.LettersIntervalService;
import ru.cft.task_backend.models.enums.IntervalDataType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntervalController {
    private final String POST_INTERVAL = "/api/v1/intervals/merge";
    private final String FIND_MIN_INTERVAL = "/api/v1/intervals/min";

    private final DigitsIntervalService digitsIntervalService;
    private final LettersIntervalService lettersIntervalService;

    @PostMapping(POST_INTERVAL)
    public void addInterval(@RequestParam String kind, @RequestBody ArrayList<Object[]> request) {
        if (IntervalDataType.valueOf(kind) == IntervalDataType.digits) {
            digitsIntervalService.addNewIntervals(request);
        } else if (IntervalDataType.valueOf(kind) == IntervalDataType.letters) {
            lettersIntervalService.addNewIntervals(request);
        }
    }

    @GetMapping(FIND_MIN_INTERVAL)
    public MinDigitsIntervalResponse getMinInterval(@RequestParam String kind){
        if(IntervalDataType.valueOf(kind) == IntervalDataType.digits){
            return digitsIntervalService.findMinInterval();
        } else if (IntervalDataType.valueOf(kind) == IntervalDataType.letters){
            return lettersIntervalService.findMinInterval();
        }

        throw  new BadRequestException("lhjgly");
    }
}
