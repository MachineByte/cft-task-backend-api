package ru.cft.task_backend.core.utils;

import org.junit.jupiter.api.Test;
import ru.cft.task_backend.core.exceptions.BadStateException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class IntervalProcessorTest {

    List<Integer[]> correctIntInput = Arrays.asList(
            new Integer[]{-5, 6},
            new Integer[]{3, 10},
            new Integer[]{17, 19},
            new Integer[]{20, 24},
            new Integer[]{24, 27}
    );

    List<Integer[]> correctIntOutput = Arrays.asList(
            new Integer[]{-5, 10},
            new Integer[]{17, 19},
            new Integer[]{20, 27}
    );

    List<Integer[]> insufficientIntInput = Arrays.asList(
            new Integer[]{-5, 6},
            new Integer[]{3},
            new Integer[]{17, 19},
            new Integer[]{20, 24},
            new Integer[]{24, 27}
    );

    List<Integer[]> oversizeIntInput = Arrays.asList(
            new Integer[]{-5, 6},
            new Integer[]{3},
            new Integer[]{17, 19},
            new Integer[]{20, 24},
            new Integer[]{24, 27}
    );

    List<Character[]> correctCharInput = Arrays.asList(
            new Character[]{'a', 'g'},
            new Character[]{'b', 'e'},
            new Character[]{'e', 'f'},
            new Character[]{'x', 'y'},
            new Character[]{'y', 'z'}
    );

    List<Character[]> correctCharOutput = Arrays.asList(
            new Character[]{'a', 'g'},
            new Character[]{'x', 'z'}
    );

    List<Character[]> insufficientCharInput = Arrays.asList(
            new Character[]{'a', 'g'},
            new Character[]{'b', 'e'},
            new Character[]{'e', 'f'},
            new Character[]{'x', 'y'},
            new Character[]{'y'}
    );

    List<Character[]> oversizeCharInput = Arrays.asList(
            new Character[]{'a', 'g'},
            new Character[]{'b', 'e'},
            new Character[]{'e', 'f'},
            new Character[]{'x', 'y', 't'}
    );


    Comparator<Integer[]> intComparator = Comparator.comparingInt(arr -> arr[0]);
    Comparator<Character[]> charComparator = Comparator.comparing(arr -> arr[0]);


    @Test
    void testIntInput() {
        List<Integer[]> output = IntervalProcessor.collapseIntersections(correctIntInput, intComparator);

        assertThat(output.size()).isEqualTo(correctIntOutput.size());

        for (int i = 0; i < output.size(); i++) {
            assertArrayEquals(output.get(i),correctIntOutput.get(i));
        }

    }

    @Test
    void testCharInput() {
        List<Character[]> output = IntervalProcessor.collapseIntersections(correctCharInput, charComparator);

        assertThat(output.size()).isEqualTo(correctCharOutput.size());

        for (int i = 0; i < output.size(); i++) {
            assertArrayEquals(output.get(i),correctCharOutput.get(i));
        }
    }

    @Test
    void testInsufficientInput() {
        assertThrows(
                BadStateException.class,
                () -> IntervalProcessor.collapseIntersections(insufficientIntInput, intComparator)
        );

        assertThrows(
                BadStateException.class,
                () -> IntervalProcessor.collapseIntersections(insufficientCharInput, charComparator)
        );
    }

    @Test
    void testOversizeInput() {
        assertThrows(
                BadStateException.class,
                () -> IntervalProcessor.collapseIntersections(oversizeIntInput, intComparator)
        );

        assertThrows(
                BadStateException.class,
                () -> IntervalProcessor.collapseIntersections(oversizeCharInput, charComparator)
        );

    }
}
