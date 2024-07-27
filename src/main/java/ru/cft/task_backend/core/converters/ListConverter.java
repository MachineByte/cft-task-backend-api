package ru.cft.task_backend.core.converters;

import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.newInstance;

public class ListConverter {
    public static <T> List<T[]> convertToTypedList(List<Object[]> input, Class<T> clazz){
        List<T[]> ret = new ArrayList<>();
        for (Object[] i : input){
            ret.add(convertToArray(i, clazz));
        }
        return ret;
    }

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
