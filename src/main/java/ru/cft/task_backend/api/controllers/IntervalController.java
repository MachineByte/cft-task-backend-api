package ru.cft.task_backend.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.models.IntervalDataType;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class IntervalController {
    private final String POST_INTERVAL = "/api/v1/intervals/merge";

    private final DigitsIntervalService intervalService;

    @PostMapping(POST_INTERVAL)
    public void addInterval(@RequestParam String kind, @RequestBody List<int[]> request){
        if(Objects.equals(kind, IntervalDataType.DIGITS.toString().toLowerCase())){
            intervalService.addNewIntervals(request);
        }
    }
}
