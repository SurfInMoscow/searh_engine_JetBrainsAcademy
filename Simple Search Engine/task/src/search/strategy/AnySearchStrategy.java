package search.strategy;

import java.util.*;

public class AnySearchStrategy implements SearchStrategy<String, Set<Integer>> {
    @Override
    public void search(String searchData, Map<String, Set<Integer>> indexMap, List<String> lines) {
        List<String> words = Arrays.asList(searchData.split("\\s+"));

        if (!words.isEmpty()) {
            List<String> tmp = new ArrayList<>();

            for (int i = 0; i < words.size(); i++) {
                if (indexMap.containsKey(words.get(i))) {
                    indexMap.get(words.get(i)).forEach(o -> {
                        tmp.add(lines.get(o));
                    });
                }
            }

            if (!tmp.isEmpty()) {
                System.out.printf("%d entries found:\n", tmp.size());
                tmp.forEach(System.out::println);
            } else {
                System.out.println("No match.");
            }
        } else {
            System.out.println("No match.");
        }
    }
}
