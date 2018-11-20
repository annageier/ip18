package searchengine;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class SearchController {

    @CrossOrigin(origins = "*")
    @RequestMapping("/search")
    public List<Website> search(@RequestParam(value="query", defaultValue=" ") String query) {
        
        if (query == null) {
            return new ArrayList<Website>();
        }

        System.out.println("Handling request for query word \"" + query + "\"");

        List<Website> resultList = SearchEngine.queryHandler.getMatchingWebsites(query);
        System.out.println("Found " + resultList.size() + " websites.");
        return resultList;
    }
}
