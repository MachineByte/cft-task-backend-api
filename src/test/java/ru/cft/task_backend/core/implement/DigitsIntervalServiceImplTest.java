package ru.cft.task_backend.core.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cft.task_backend.api.dto.MinDigitsIntervalResponse;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.repositories.DigitsIntervalRepository;
import ru.cft.task_backend.models.DigitsIntervalEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DigitsIntervalServiceImplTest {

    @Mock
    private DigitsIntervalRepository intervalRepository;

    @InjectMocks
    private DigitsIntervalServiceImpl digitsIntervalService;

    @Test
    void testFindMinInterval() {
        DigitsIntervalEntity mockEntity = DigitsIntervalEntity.builder()
                .start(1)
                .end(10)
                .build();
        when(intervalRepository.findMinInterval()).thenReturn(Optional.of(mockEntity));

        MinDigitsIntervalResponse response = digitsIntervalService.findMinInterval();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getStart()),
                () -> assertEquals(10, response.getEnd())
        );

    }

    @Test
    void testFindMinInterval_NoIntervalFound() {
        when(intervalRepository.findMinInterval()).thenReturn(Optional.empty());

        MinDigitsIntervalResponse response = digitsIntervalService.findMinInterval();

        assertNull(response);
    }

    @Test
    void testAddNullInterval() {
        Executable nullRequest = () -> digitsIntervalService.addNewIntervals(null);
        Executable emptyListRequest = () -> digitsIntervalService.addNewIntervals(null);

        assertAll(
                () -> assertThrows(BadRequestException.class, nullRequest),
                () -> assertThrows(BadRequestException.class, emptyListRequest)
        );
    }
}
