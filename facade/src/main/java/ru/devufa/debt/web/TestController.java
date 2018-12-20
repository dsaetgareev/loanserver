package ru.devufa.debt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
