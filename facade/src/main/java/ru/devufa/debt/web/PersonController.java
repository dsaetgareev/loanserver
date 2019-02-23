package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.service.common.impl.ChangePasswordServiceImpl;
import ru.devufa.debt.service.common.impl.PersonServiceImpl;
import ru.devufa.debt.web.dto.PersonDTO;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    @Qualifier("DTOConversionService")
    private ConversionService conversionService;

    @Autowired
    private ChangePasswordServiceImpl changePasswordService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    private String test() {
        return "wrwerwe";
    }

    @RequestMapping(path = "/{code}", method = RequestMethod.POST)
    public PersonDTO register(@RequestBody PersonDTO personDTO, @PathVariable(name = "code") String code) {
        Person person = conversionService.convert(personDTO, Person.class);
        Person createdPerson = personService.create(person, code);
        return conversionService.convert(createdPerson, PersonDTO.class);
    }

    @RequestMapping(path = "/registerRequest", method = RequestMethod.POST)
    public void registerRequest(@RequestParam(name = "telephoneNumber") String telephoneNumber) {
        personService.createRegistrationRequest(telephoneNumber);
    }

    @RequestMapping(path = "/verifyCode", method = RequestMethod.GET)
    public boolean verifyCode(@RequestParam(name = "code") String code, @RequestParam(name = "telephoneNumber") String number) {
        return personService.verifyCode(number, code);
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.GET)
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
