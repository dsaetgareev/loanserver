package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.service.common.impl.ChangePasswordServiceImpl;
import ru.devufa.debt.service.common.impl.PersonServiceImpl;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private ChangePasswordServiceImpl changePasswordService;

    @RequestMapping(path = "/{code}", method = RequestMethod.POST)
    public Person register(@RequestBody Person person, @PathVariable(name = "code") String code) {
        return personService.create(person, code);
    }

    @RequestMapping(path = "/registerRequest", method = RequestMethod.POST)
    public void registerRequest(@RequestParam(name = "telephoneNumber") String telephoneNumber) {
        personService.createRegistrationRequest(telephoneNumber);
    }


    @RequestMapping(path = "/changePassword")
    public String changePasswordRequest(@RequestParam("telephoneNumber") String telephoneNumber) {
        return changePasswordService.changePasswordRequest(telephoneNumber);
    }

    @RequestMapping(path = "/commitChangePassword", method = RequestMethod.POST)
    public void changePasswordAccept(
            @RequestParam("telephoneNumber") String telephoneNumber,
            @RequestParam("code") String code,
            @RequestParam("newPassword") String newPassword) {
        changePasswordService.changePassword(telephoneNumber, code, newPassword);
    }
}
