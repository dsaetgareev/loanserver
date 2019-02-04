package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.devufa.debt.entity.Debt;
import ru.devufa.debt.entity.DebtType;
import ru.devufa.debt.entity.Status;
import ru.devufa.debt.service.common.impl.DebtServiceImpl;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/debt")
public class DebtController {

    @Autowired
    private DebtServiceImpl debtService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Debt get(@PathVariable(name = "id") String id) {
        return null;
    }

    @RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
    public List<Debt> getList(@PathVariable(name = "type") String type, @RequestParam(value = "status", required = false) List<Status> statuses) {
        try {
            DebtType debtType = DebtType.valueOf(type);
            return debtService.findAll(debtType, statuses);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Type not found");
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Debt create(@RequestBody Debt debt) {
        return debtService.create(debt);
    }

    @RequestMapping(path = "/accept/{id}", method = RequestMethod.POST)
    public void accept(@PathVariable String id, @RequestParam boolean isAccepted) {
        debtService.accept(id, isAccepted);
    }
    @RequestMapping(path = "/pay/{id}", method = RequestMethod.POST)
    public void pay(@PathVariable String id) {
        debtService.pay(id);
    }
}
