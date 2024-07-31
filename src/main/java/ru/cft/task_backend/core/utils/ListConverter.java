package ru.cft.task_backend.core.utils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.newInstance;

/**
 * Utility class for converting lists of objects to typed lists.
 */
public class ListConverter {
    /**
     * Converts a list of object arrays to a list of typed arrays.
     *
     * @param input The list of object arrays to convert.
     * @param clazz The class of the type to convert the objects to.
     * @param <T>   The type of the elements in the arrays.
     * @return A list of typed arrays.
     */
    public static <T> List<T[]> convertToTypedList(List<Object[]> input, Class<T> clazz){
        List<T[]> resultList = new ArrayList<>();
        for (Object[] i : input){
            resultList.add(convertToArray(i, clazz));
        }
        return resultList;
    }

    /**
     * Converts an object array to a typed array.
     *
     * @param objectArray The object array to convert.
     * @param clazz       The class of the type to convert the objects to.
     * @param <T>         The type of the elements in the array.
     * @return A typed array.
     */
    public static <T> T[] convertToArray(Object[] objectArray, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) newInstance(clazz, objectArray.length);
        for (int i = 0; i < objectArray.length; i++) {
            if (clazz == Character.class && objectArray[i] instanceof String string) {
                char[] charArray = string.toCharArray();
                array[i] = clazz.cast(charArray[0]);
            } else {
                array[i] = clazz.cast(objectArray[i]);
            }
        }
        return array;
    }

}
