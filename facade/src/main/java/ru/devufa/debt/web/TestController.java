package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.service.common.PersonService;

import java.util.UUID;

@RestController
public class TestController {



    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public UUID test() {
       return UUID.randomUUID();
    }
}
