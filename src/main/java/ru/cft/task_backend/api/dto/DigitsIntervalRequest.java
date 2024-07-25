package ru.cft.task_backend.api.dto;

import lombok.Builder;
import lombok.Value;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link DigitsIntervalEntity}
 */
@Value
@Builder
public class DigitsIntervalRequest implements Serializable {
    List<int[]> intervals;
}