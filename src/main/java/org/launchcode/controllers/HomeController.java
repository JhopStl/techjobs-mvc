package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //Handler that shows the main page and a list of choices on if the user would want to see jobs
    //by searching or by a list - display options
    @RequestMapping(value = "")
    public String index(Model model) {

        HashMap<String, String> actionChoices = new HashMap<>();
        //add action choices to Hashmap
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        //adds actionChoices HashMap to model
        model.addAttribute("actions", actionChoices);

        return "index";
    }

}
