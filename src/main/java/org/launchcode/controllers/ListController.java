package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    //instantiating new HashMap - columnChoices
    static HashMap<String, String> columnChoices = new HashMap<>();

    //add list of search and list choices to columnChoices
    public ListController () {
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }
    //handler named list that returns a list of columnChoices
    @RequestMapping(value = "")
    public String list(Model model) {

        model.addAttribute("columns", columnChoices);

        return "list";
    }

    //handler named listColumnValues that uses the column value as a query param to
    //determine which values are pulled from JobData
    @RequestMapping(value = "values")

    //model.addAttribute passes data into the view (templates) from the controller

    //@RequestParam gets data out of the request (i.e. column)
    //spring will look for "column" parameter
    public String listColumnValues(Model model, @RequestParam String column) {
        //if the user clicks on "all", call the findAll method from JobData...an ArrayList of HashMap
        //add and pass
        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> Jobs = JobData.findAll();
            int itemCount = Jobs.size();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("itemCount",itemCount + " Result(s)");
            model.addAttribute("Jobs", Jobs);
            return "list-jobs";
        } else {
            ArrayList<String> items = JobData.findAll(column);
            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            return "list-column";
        }

    }
    //handler takes in two query param, column and value, this displays the information when a user
    //clicks on a link...e.g. Location is Saint Louis, user clicks on Saint Louis to see the jobs
    //in Saint Louis
    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
            @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> Jobs = JobData.findByColumnAndValue(column, value);
        int itemCount = Jobs.size();
        model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        model.addAttribute("itemCount",itemCount + " Result(s)");
        model.addAttribute("Jobs", Jobs);

        return "list-jobs";
    }
}
