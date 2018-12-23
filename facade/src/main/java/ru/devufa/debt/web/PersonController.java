package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.service.common.ChangePasswordService;
import ru.devufa.debt.service.common.PersonService;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ChangePasswordService changePasswordService;

    @PostMapping(path = "/account/register")
    public Person register(@RequestBody Person person) {
        return personService.create(person);
    }


    @GetMapping(path = "/account/changePasswordRequest")
    public String changePasswordRequest(@RequestParam("telephoneNumber") String telephoneNumber) {
        return changePasswordService.changePasswordRequest(telephoneNumber);
    }

    @GetMapping(path = "/account/acceptPasswordRequest")
    public void changePasswordAccept(
            @RequestParam("telephoneNumber") String telephoneNumber,
            @RequestParam("code") String code,
            @RequestParam("newPassword") String newPassword) {
        changePasswordService.changePassword(telephoneNumber, code, newPassword);
    }
}
