package com.galaev.classification.controller;

import com.galaev.classification.model.FormatDescription;
import com.galaev.classification.model.Parameters;
import com.galaev.classification.model.Result;
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
public class JsonController {

    @RequestMapping(value = "/{name}/title", method = RequestMethod.GET, produces = "text/plain")
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

    @RequestMapping(value = "/{name}/comment", method = RequestMethod.GET)
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
                model.addObject("title", "Simultaneous Threshold Interaction Modeling Algorithm (STIMA)");
                model.addObject("content", "STIMA is a classification tool that estimates a regression trunk model," +
                        " which is an integration of a regression tree and a multiple regression model");
                break;
            default:
                model.addObject("title", "No such method implemented here");
                model.addObject("content", "");
        }
        return model;
    }

    @RequestMapping(value = "/{name}/parameters", method = RequestMethod.GET)
    public @ResponseBody Parameters getParameters(@PathVariable String name) {
        Parameters parameters = new Parameters();
        switch (name) {
            case "quint":
                parameters.put("context", "MVContext");
                parameters.put("crit", "int");
                parameters.put("maxl", "int");
                parameters.put("a1", "int");
                parameters.put("a2", "int");
                parameters.put("w1", "int");
                parameters.put("w2", "int");
                parameters.put("bootstrap", "boolean");
                parameters.put("bnum", "int");
                parameters.put("dmin", "double");
                parameters.put("c", "double");
                break;
            case "stima":
                parameters.put("context", "MVContext");
                parameters.put("maxsplit", "int");
                parameters.put("first", "int");
                parameters.put("vfold", "int");
                parameters.put("cv", "int");
                parameters.put("minbucket", "int");
                parameters.put("mincrit", "int");
                parameters.put("c", "double");
                break;
            default:
                break;
        }
        return parameters;
    }


    @RequestMapping(value = "{name}/input", method = RequestMethod.GET)
    public @ResponseBody
    FormatDescription getInputFormat(@PathVariable String name) {
        FormatDescription description = new FormatDescription();
        description.setName(name);
        switch (name) {
            case "quint":
                description.setDescription("JSON");
                break;
            case "stima":
                description.setDescription("JSON");
                break;
            default:
                description.setDescription("No such method implemented here");
                break;
        }

        return description;
    }

    @RequestMapping(value = "{name}/output", method = RequestMethod.GET)
    public @ResponseBody
    FormatDescription getOutputFormat(@PathVariable String name) {
        FormatDescription description = new FormatDescription();
        description.setName(name);
        switch (name) {
            case "quint":
                description.setDescription("JSON");
                break;
            case "stima":
                description.setDescription("JSON");
                break;
            default:
                description.setDescription("No such method implemented here");
                break;
        }

        return description;
    }

    @RequestMapping(value = "{name}/execute", method = RequestMethod.POST)
    public @ResponseBody Result getResult(@PathVariable String name) {
        Result result = new Result();
        switch (name) {
            case "quint":
                result.setResult("Qualitative Interaction Trees");
                break;
            case "stima":
                result.setResult("Simultaneous Threshold Interaction Modeling Algorithm");
                break;
            default:
                result.setResult("No such method implemented here");
                break;
        }
        return result;
    }
}
