package search;

import search.strategy.AllSearchStrategy;
import search.strategy.AnySearchStrategy;
import search.strategy.ContextSearchStrategy;
import search.strategy.NoneSearchStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SearchEngine {
    private final List<String> lines = new ArrayList<>();

    private final Map<String, Set<Integer>> map = new HashMap<>();

    private final ContextSearchStrategy contextSearch = new ContextSearchStrategy<String, Set<Integer>>();

    public SearchEngine(String path) throws FileNotFoundException {
        init(path);
    }

    private void init(String path) throws FileNotFoundException {
        try (var scanner = new Scanner(new File(path));
             var consoleScanner = new Scanner(System.in)) {

            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }

            for (int i = 0; i < lines.size(); i++) {
                var words = lines.get(i).split("\\s+");

                for (int j = 0; j < words.length; j++) {
                    if (map.containsKey(words[j].toLowerCase())) {
                        map.get(words[j].toLowerCase()).add(i);
                    } else {
                        Set<Integer> idx = new TreeSet<>();
                        idx.add(i);
                        map.put(words[j].toLowerCase(), idx);
                    }
                }
            }

            process(consoleScanner);
        }
    }

    private void process(Scanner scanner) {
        boolean run = true;

        while (run) {
            System.out.println("=== Menu ===" + "\n" +
                    "1. Find a value" + "\n" +
                    "2. Print all people" + "\n" +
                    "0. Exit");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 0:
                    System.out.println("Bye!");
                    run = false;
                    break;
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    var type = scanner.nextLine();

                    switch (type) {
                        case "ALL":
                            contextSearch.setStrategy(new AllSearchStrategy());
                            invokeContextSearch(scanner);
                            break;
                        case "ANY":
                            contextSearch.setStrategy(new AnySearchStrategy());
                            invokeContextSearch(scanner);
                            break;
                        case "NONE":
                            contextSearch.setStrategy(new NoneSearchStrategy());
                            invokeContextSearch(scanner);
                            break;
                        default:
                            System.out.println("Unknown strategy plan!");
                    }
                    break;
                case 2:
                    System.out.println("=== List of values ===");
                    lines.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    private void invokeContextSearch(Scanner scanner) {
        String searchData;
        System.out.println("Enter data to search:");
        searchData = scanner.nextLine().toLowerCase();
        contextSearch.invoke(searchData, map, lines);
    }
}
