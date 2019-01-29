package ru.devufa.debt.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.person.PersonRepository;
import ru.devufa.debt.repository.person.PersonRepositoryService;

@Component
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private PersonRepositoryService personRepositoryService;

    @Override
    public Person getCurrentPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            String currentPerson = authentication.getName();
            return personRepositoryService.findFirstByTelephoneNumber(currentPerson);
        }
        return null;
    }
}
