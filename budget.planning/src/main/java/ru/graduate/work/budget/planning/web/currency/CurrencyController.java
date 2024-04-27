package ru.graduate.work.budget.planning.web.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/search")
    public ResponseEntity<List<Currency>> currenciesSearch(@RequestParam(name = "title", required = false) String title) {
        final List<Currency> currencies = currencyService.listCurrencies((title));
        return currencies != null && !currencies.isEmpty()
                ? new ResponseEntity<>(currencies, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> currencyInfo(@PathVariable Long id) {
        Currency currency = currencyService.getCurrencyById(id);
        return currency != null
                ? new ResponseEntity<>(currency, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCurrency(@RequestBody Currency currency) {
        currencyService.saveCurrency(currency);
        return new ResponseEntity<>(currency, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editCurrency(@RequestBody Currency currency) {
        currencyService.editCurrency(currency);
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }
}
