package searchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * The main class of our search engine program.
 *
 * @author Willard Rafnsson
 * @author Martin Aumüller
 * @author Leonid Rusnac
 */
@SpringBootApplication
public class SearchEngine {
    public static QueryHandler queryHandler;

    public static void main(String[] args) {
        System.out.println("Welcome to the SearchEngine!");

        if (args.length < 1) {
            System.out.println("Error: Filename is missing");
            return;
        }

        Index idx = new SimpleIndex();
        queryHandler = new QueryHandler(idx);
        long t1 = System.nanoTime();
        List<Website> sites = FileHelper.parseFile(args[0]);
        //List<Website> sites = FileHelper.parseFile("/home/willard/Documents/teaching/introductory-programming/workshop/REPO/ip18/data/enwiki-small.txt");
        idx.build(sites);
        System.out.println("Done building index.");
        long t2 = System.nanoTime();
        System.out.println("Processing the data set and building the index took "
                           + (t2 - t1) / 10e6 + " milliseconds.");
        // run the search engine
        SpringApplication.run(SearchEngine.class, args);
    }
}
