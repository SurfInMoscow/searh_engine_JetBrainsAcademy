package search;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args[0].equals("--data") && args[1] != null && !args[1].equals("")) {
            new SearchEngine(args[1]);

        } else {
            System.out.println("Run program with arguments '--data' and 'filename'");
            System.exit(2);
        }
    }
}
