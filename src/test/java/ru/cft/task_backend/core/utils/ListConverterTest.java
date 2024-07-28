package ru.cft.task_backend.core.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListConverterTest {

    @Test
    public void testConvertToToIntegerArray() {
        Object[] input = {1, 2, 3};
        Integer[] expected = {1, 2, 3};
        Integer[] result = ListConverter.convertToArray(input, Integer.class);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testConvertToStringArray() {
        Object[] input = {"a", "b", "c"};
        String[] expected = {"a", "b", "c"};
        String[] result = ListConverter.convertToArray(input, String.class);
        assertArrayEquals(expected, result);
    }


    @Test
    public void testConvertToTypedIntegerList() {
        List<Object[]> input = new ArrayList<>();
        input.add(new Object[]{1, 2, 3});
        input.add(new Object[]{4, 5, 6});

        List<Integer[]> expected = new ArrayList<>();
        expected.add(new Integer[]{1, 2, 3});
        expected.add(new Integer[]{4, 5, 6});

        List<Integer[]> result = ListConverter.convertToTypedList(input, Integer.class);
        assertEquals(expected.size(), result.size());

        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    public void testConvertToTypedStringList() {
        List<Object[]> input = new ArrayList<>();
        input.add(new Object[]{"a", "b", "c"});
        input.add(new Object[]{"d", "e", "f"});

        List<String[]> expected = new ArrayList<>();
        expected.add(new String[]{"a", "b", "c"});
        expected.add(new String[]{"d", "e", "f"});

        List<String[]> result = ListConverter.convertToTypedList(input, String.class);
        assertEquals(expected.size(), result.size());

        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), result.get(i));
        }
    }
}
