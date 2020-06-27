package search.strategy;

import java.util.*;

public class NoneSearchStrategy implements SearchStrategy<String, Set<Integer>> {
    @Override
    public void search(String searchData, Map<String, Set<Integer>> indexMap, List<String> lines) {
        List<String> words = Arrays.asList(searchData.split("\\s+"));

        if (words.size() >= 1 && !words.get(0).equals("")) {

            Set<Integer> set = new HashSet<>();

            for (var entry : indexMap.entrySet()) {
                set.addAll(entry.getValue());
            }

            for (int i = 0; i < words.size(); i++) {
                set.removeAll(indexMap.get(words.get(i)));
            }

            System.out.printf("%d entries found:\n", set.size());

            set.forEach(i -> System.out.println(lines.get(i)));
        } else {
            System.out.println("No match.");
        }
    }
}
