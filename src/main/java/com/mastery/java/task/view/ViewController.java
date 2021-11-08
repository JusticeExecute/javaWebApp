package com.mastery.java.task.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(path = "/")
    public String view() {
        return "home";
    }

    @GetMapping(path = "/create")
    public String createView() {
        return "create";
    }

    @GetMapping(path = "/update")
    public String updateView() {
        return "update";
    }

    @GetMapping(path = "/delete")
    public String deleteView() {
        return "delete";
    }

    @GetMapping(path = "/search")
    public String searchView() {
        return "search";
    }
}
