package ru.cft.task_backend.core.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cft.task_backend.api.dto.MinLettersIntervalResponse;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.repositories.LettersIntervalRepository;
import ru.cft.task_backend.models.LettersIntervalEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LettersIntervalServiceImplTest {

    @Mock
    private LettersIntervalRepository intervalRepository;

    @InjectMocks
    private LettersIntervalServiceImpl digitsIntervalService;

    @Test
    void testFindMinInterval() {
        LettersIntervalEntity mockEntity = LettersIntervalEntity.builder()
                .start('a')
                .end('b')
                .build();
        when(intervalRepository.findMinInterval()).thenReturn(Optional.of(mockEntity));

        MinLettersIntervalResponse response = digitsIntervalService.findMinInterval();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals('a', response.getStart()),
                () -> assertEquals('b', response.getEnd())
        );

    }

    @Test
    void testFindMinInterval_NoIntervalFound() {
        when(intervalRepository.findMinInterval()).thenReturn(Optional.empty());

        MinLettersIntervalResponse response = digitsIntervalService.findMinInterval();

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
