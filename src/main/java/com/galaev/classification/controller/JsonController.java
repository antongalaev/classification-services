package com.galaev.classification.controller;

import com.galaev.classification.model.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class handles client requests
 * and returns responses with JSON objects.
 *
 * @author Anton Galaev
 */
@Controller
@RequestMapping("/solvers/")
public class JsonController {

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
