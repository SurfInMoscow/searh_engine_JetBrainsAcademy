package search.strategy;

import java.util.List;
import java.util.Map;

public class ContextSearchStrategy<K, V> {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void invoke(String searchData, Map<K, V> indexMap, List<K> lines) {
        strategy.search(searchData, indexMap, lines);
    }
}
