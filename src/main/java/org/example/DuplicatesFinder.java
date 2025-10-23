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

    /*
    * Explanation:
    *  - seen: stores the elements seen by the function
    *  - duplicates: stores the elements that are duplicated in the list
    *
    * For each element of list, I check whether it has been seen or not. If it has, then I check if it is on the duplicated list to add it if possible.
    * If an element has not been seen, I add it to the seen list.
    *
    *
    * */
}
