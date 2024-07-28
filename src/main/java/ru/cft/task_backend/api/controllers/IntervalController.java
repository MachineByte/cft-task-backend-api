package ru.cft.task_backend.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.task_backend.api.dto.MinIntervalResponse;
import ru.cft.task_backend.core.services.IntervalDispatchService;
import ru.cft.task_backend.models.enums.IntervalDataType;

import java.util.ArrayList;

@Tag(name = "Interval Controller", description = "API for managing intervals of digits and letters")
@RestController
@RequiredArgsConstructor
public class IntervalController {
    private final String POST_INTERVAL = "/api/v1/intervals/merge";
    private final String FIND_MIN_INTERVAL = "/api/v1/intervals/min";

    private final IntervalDispatchService intervalService;

    @Operation(summary = "Add new intervals", description = "Adds new intervals of the specified kind (digits or letters).")
    @PostMapping(POST_INTERVAL)
    public void addInterval(@RequestParam String kind, @RequestBody ArrayList<Object[]> request) {
        intervalService.addNewIntervals(request, kind);

    }

    @Operation(summary = "Find minimum interval", description = "Finds the minimum interval of the specified kind (digits or letters).")
    @GetMapping(FIND_MIN_INTERVAL)
    public MinIntervalResponse getMinInterval(@RequestParam String kind){
        return intervalService.findMinInterval(kind);
    }
}
