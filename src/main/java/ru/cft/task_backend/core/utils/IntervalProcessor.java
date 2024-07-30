package ru.cft.task_backend.core.utils;

import ru.cft.task_backend.core.exceptions.BadStateException;

import java.util.*;
import java.util.stream.Collectors;

public class IntervalProcessor {
    /**
     * The method first sorts the List based on the first elements of each subarray.
     * Then, it iterates over each element, checking if it intersects with the next element.
     * If so, the current interval is expanded to the upper limit of the current element.
     * If there is no intersection, it assumes that the desired interval has been found and adds it to the output List.
     * <p>
     * Algorithm CPU complexity - O(n log(n))
     * Algorithm Memory complexity - O(n)
     *
     * @param list The list of digit intervals to collapse.
     * @return A list of collapsed digit intervals.
     * @throws BadStateException if the intervals are not valid.
     */
    public static <T extends Comparable<T>> List<T[]> collapseIntersections(List<T[]> list, Comparator<T[]> comparator) {
        List<T[]> result = new ArrayList<>();
        list = list.stream().sorted(comparator).collect(Collectors.toList()); // Sorting an input list

        T[] currentInterval = list.getFirst();

        if (currentInterval.length != 2) {
            throw new BadStateException("Incorrect interval: " + Arrays.toString(currentInterval));
        }

        for (int i = 1; i < list.size(); i++) {
            T[] nextInterval = list.get(i);

            if (nextInterval.length != 2) {
                throw new BadStateException("Incorrect interval: " + Arrays.toString(nextInterval));
            }

            if (currentInterval[1].compareTo(nextInterval[0]) < 0) { // Intervals don't overlap
                result.add(currentInterval);
                currentInterval = nextInterval;
            } else if (currentInterval[1].compareTo(nextInterval[1]) <= 0) { // Intervals overlap
                currentInterval[1] = nextInterval[1]; // Merge intervals
            }
        }

        result.add(currentInterval); // Add the last interval

        return result;
    }

}
