package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DuplicatesFinder {
    public static <T extends Serializable> Stream<T> findDuplicates(Stream<T> list) {
        List<T> seen = new ArrayList<>();
        List<T> duplicates = new ArrayList<>();

        list.forEachOrdered(item -> {
            if(seen.contains(item)){
                if(!duplicates.contains(item)) duplicates.add(item);
            } else seen.add(item);
        });

        return duplicates.stream();
    }
}
