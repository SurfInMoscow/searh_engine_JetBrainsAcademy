package search.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllSearchStrategy implements SearchStrategy<String, Set<Integer>> {

    @Override
    public void search(String searchData, Map<String, Set<Integer>> indexMap, List<String> lines) {
        List<String> words = Arrays.asList(searchData.split("\\s+"));

        if (!words.isEmpty()) {
            if (words.size() == 1) {
                if (indexMap.containsKey(words.get(0))) {
                    System.out.printf("%d entries found:\n", indexMap.get(words.get(0)).size());
                    indexMap.get(words.get(0)).forEach(i -> System.out.println(lines.get(i)));
                } else {
                    System.out.println("No match.");
                }
            } else if (indexMap.containsKey(words.get(0))) {
                Set<Integer> set = indexMap.get(words.get(0));

                for (int i = 1; i < words.size(); i++) {
                    set.retainAll(indexMap.get(words.get(i)));
                }

                System.out.printf("%d entries found:\n", set.size());
                set.forEach(i -> System.out.println(lines.get(i)));
            } else {
                System.out.println("No match.");
            }
        } else {
            System.out.println("No match.");
        }

    }
}
