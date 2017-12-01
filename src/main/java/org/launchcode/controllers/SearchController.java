package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
    //need to create a handler that passes two params, value and column
    //pass to the view (Search.html)

    //need to pull in request of Search category (eg Location) from from after submit
    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String listJobsBySearch(Model model, @RequestParam String searchType,
                                   @RequestParam String searchTerm) {
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> Jobs = JobData.findByValue(searchTerm);
            int itemCount = Jobs.size();
            model.addAttribute("Jobs", Jobs);
            model.addAttribute("itemCount",itemCount + " Result(s)");
            model.addAttribute("columns", ListController.columnChoices);

            return "search";

        } else {
        ArrayList<HashMap<String, String>> Jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        int itemCount = Jobs.size();
        model.addAttribute("Jobs", Jobs);
        model.addAttribute("itemCount",itemCount + " Result(s)");
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }
}

}
