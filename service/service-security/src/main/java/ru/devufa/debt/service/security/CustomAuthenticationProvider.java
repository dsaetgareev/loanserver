package ru.devufa.debt.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.person.PersonRepository;
import ru.devufa.debt.repository.person.PersonRepositoryService;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private PersonRepositoryService personRepositoryService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepositoryService.findFirstByTelephoneNumber(name);
        if (person == null || person.getPassword() == null) return null;
        if (name.equals(person.getTelephoneNumber()) && passwordEncoder.matches(password, person.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
