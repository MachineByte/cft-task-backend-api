package ru.cft.task_backend.core.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cft.task_backend.core.exceptions.BadRequestException;
import ru.cft.task_backend.core.services.DigitsIntervalService;
import ru.cft.task_backend.core.services.LettersIntervalService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IntervalDispatchServiceImplTest {
    @Mock
    DigitsIntervalService digitsIntervalService;
    @Mock
    LettersIntervalService lettersIntervalService;

    @InjectMocks
    IntervalDispatchServiceImpl intervalDispatchService;

    List<Object[]> request = Arrays.asList(
            new Integer[]{-5, 6},
            new Integer[]{3, 10},
            new Integer[]{17, 19},
            new Integer[]{20, 24},
            new Integer[]{24, 27}
    );

    @Test
    void addNewIntervalsDataTypesTestTest() {
        try {
            intervalDispatchService.addNewIntervals(request, "digits");
        } catch (Exception e){
            fail("Correct request /digits/ causes: " + e.getMessage());
        }

        try {
            intervalDispatchService.addNewIntervals(request, "letters");
        } catch (Exception e){
            fail("Correct request /letters/ causes: " + e.getMessage());
        }

        assertThrows(
                BadRequestException.class,
                ()->intervalDispatchService.addNewIntervals(request, ":)")
        );
    }
}
