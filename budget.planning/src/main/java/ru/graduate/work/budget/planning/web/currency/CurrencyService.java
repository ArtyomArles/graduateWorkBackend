package ru.graduate.work.budget.planning.web.currency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    public List<Currency> listCurrencies(String title) {
        if (title != "" && title != null)
            return currencyRepository.findByTitle(title, Sort.by("id").descending());
        return currencyRepository.findAll(Sort.by("id").descending());
    }

    public Currency getCurrencyById(Long id) {
        return currencyRepository.findById(id).orElse(null);
    }

    public void saveCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    public void editCurrency(Currency currency) {
        this.saveCurrency(currency);
    }
}
