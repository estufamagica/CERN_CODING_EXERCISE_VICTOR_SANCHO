package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class DuplicatesFinder {
    private static final int BATCH_SIZE = 4;
    private final File tempFile;

    public DuplicatesFinder() throws IOException {
        this.tempFile = File.createTempFile("seen", ".tmp");
        this.tempFile.deleteOnExit();
    }

    public <T extends Serializable> Stream<T> findDuplicates(Stream<T> list) {
        Iterator<T> iterator = list.iterator();
        Set<T> duplicates = new LinkedHashSet<>();
        Set<String> seenGlobal = new HashSet<>();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.tempFile, true))){

            while (iterator.hasNext()){
                List<T> batch = new ArrayList<>(BATCH_SIZE);
                for (int i = 0; i < BATCH_SIZE && iterator.hasNext(); i++){
                    batch.add(iterator.next());
                }

                Set<String> seenBatch = new HashSet<>();

                for (T item: batch){
                    String key = item.toString();
                    if(seenBatch.contains(key) || seenGlobal.contains(key)){
                        duplicates.add(item);
                    } else {
                        seenBatch.add(key);
                    }
                }

                for(String s: seenBatch){
                    seenGlobal.add(s);
                    writer.write(s);
                    writer.newLine();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return duplicates.stream();
    }

    /*
    * Explanation for Stage 1:
    *  - seen: stores the elements seen by the function
    *  - duplicates: stores the elements that are duplicated in the list
    *
    * For each element of list, I check whether it has been seen or not. If it has, then I check if it is on the duplicated list to add it if possible.
    * If an element has not been seen, I add it to the seen list.
    *
    * Stage 2:
    * For stage 2 I have decided to take a batch approach to the matter. As I am memory bounded I have decided to read the given dataSets in batches,
    * limiting the amount of memory in use and persisting my information in a temp file throughout the different readings of the dataSet. Note that the
    * batchSize was chosen arbitrarily. The overall functioning of the algorithm stays the same as in Stage 1, therefore there won't be needed any further
    * unitary tests, although I might add that some heavy memory testing could be useful for testing the algorithm.
    *
    *
    * */
}
