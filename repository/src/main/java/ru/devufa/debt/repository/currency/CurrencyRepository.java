package ru.devufa.debt.repository.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devufa.debt.entity.Currency;

import java.util.UUID;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    Currency findByCurrencyName(String name);
}
