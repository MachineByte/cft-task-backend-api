package ru.cft.task_backend.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MinLettersIntervalResponse {
    char start;
    char end;
}
