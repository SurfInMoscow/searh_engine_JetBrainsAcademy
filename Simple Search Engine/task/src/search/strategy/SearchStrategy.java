package search.strategy;

import java.util.List;
import java.util.Map;

public interface SearchStrategy<K, V> {
    void search(String searchData, Map<K, V> indexMap, List<K> lines);
}
