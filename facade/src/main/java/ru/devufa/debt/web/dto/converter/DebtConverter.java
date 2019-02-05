package ru.devufa.debt.web.dto.converter;

import org.springframework.core.convert.converter.Converter;
import ru.devufa.debt.entity.Debt;
import ru.devufa.debt.entity.DebtType;
import ru.devufa.debt.web.dto.DebtDTO;

public class DebtConverter implements Converter<Debt, DebtDTO> {
    @Override
    public DebtDTO convert(Debt debt) {
        DebtDTO debtDTO = new DebtDTO(debt.getId().toString(), debt.getCount(), debt.getCurrency().getCurrencyName(), debt.getComment(), debt.getStatus().name());
        if (debt.getDebtType() == DebtType.DEBT) {
            debtDTO.setBorrowerNumber(debt.getInitiator().getTelephoneNumber());
            debtDTO.setCreditorNumber(debt.getReceiver().getTelephoneNumber());
        } else {
            debtDTO.setBorrowerNumber(debt.getReceiver().getTelephoneNumber());
            debtDTO.setCreditorNumber(debt.getInitiator().getTelephoneNumber());
        }
        return debtDTO;
    }
}
