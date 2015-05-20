package com.galaev.classification.controller;

import com.galaev.classification.model.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class handles client requests
 * and returns responses with JSON objects.
 *
 * @author Anton Galaev
 */
@Controller
@RequestMapping("/solvers/")
public class JsonController {

    @RequestMapping(value = "{name}/title", method = RequestMethod.GET, produces = "text/plain")
    public @ResponseBody String getTitle(@PathVariable String name) {
        switch (name) {
            case "quint":
                return "Qualitative Interaction Trees";
            case "stima":
                return "Simultaneous Threshold Interaction Modeling Algorithm";
            default:
                return "No such method implemented here";
        }
    }

    @RequestMapping(value = "{name}/comment", method = RequestMethod.GET)
    public ModelAndView getComment(@PathVariable String name) {
        ModelAndView model = new ModelAndView("comment");
        switch (name) {
            case "quint":
                model.addObject("title", "Qualitative Interaction Trees (QUINT)");
                model.addObject("content", "Quint is a subgroup analysis tool, " +
                        "suitable for data from a two-arm randomized controlled trial. " +
                        "Grows a qualitative interaction tree. ");
                break;
            case "stima":
                model.addObject("title", "Simultaneous Threshold Interaction Modeling Algorithm");
                model.addObject("content", "STIMA is a classification tool that estimates a regression trunk model," +
                        " which is an integration of a regression tree and a multiple regression model");
                break;
            default:
                model.addObject("title", "No such method implemented here");
                model.addObject("content", "");
        }
        return model;
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public @ResponseBody Description getDescription(@PathVariable String name) {
        Description description = new Description();
        description.setName(name);

        switch (name) {
            case "quint":
                description.setDescription("Qualitative Interaction Trees");
                description.setParameters(new String[]{"context", "maxsplit", "first"});
                break;
            case "stima":
                description.setDescription("Simultaneous Threshold Interaction Modeling Algorithm");
                description.setParameters(new String[]{"context", "crit", "maxl"});
                break;
            default:
                description.setDescription("No such method implemented here");
                break;
        }

        return description;
    }
}
