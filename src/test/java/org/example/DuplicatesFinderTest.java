package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DuplicatesFinderTest {
    @Test
    void shouldReturnDuplicatesList(){
        List<String> dataSet = List.of("a", "b", "c", "a", "f", "c");
        List<String> expected = List.of("a", "c");

        List<String> result = DuplicatesFinder.findDuplicates(dataSet.stream()).toList();

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnNoDuplicates(){
        List<String> dataSet = List.of("a", "b", "c", "d", "e", "f");

        List<String> result = DuplicatesFinder.findDuplicates(dataSet.stream()).toList();

        assertEquals(List.of(), result);
    }

    @Test
    void shouldReturnOneDuplicate(){
        List<String> dataSet = List.of("a", "a", "a", "a", "a", "c");
        List<String> expected = List.of("a");

        List<String> result = DuplicatesFinder.findDuplicates(dataSet.stream()).toList();

        assertEquals(expected, result);
    }

    @Test
    void shouldIgnoreEmptyStream(){
        List<String> dataSet = List.of();

        List<String> result = DuplicatesFinder.findDuplicates(dataSet.stream()).toList();

        assertEquals(List.of(), result);
    }

}