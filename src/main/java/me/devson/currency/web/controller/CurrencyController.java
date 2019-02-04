package me.devson.currency.web.controller;

import me.devson.currency.web.dto.CurrencyApiResponse;
import me.devson.currency.web.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String currencyView(Model model) {
        return "currency";
    }

    @GetMapping("/currency")
    public ResponseEntity<CurrencyApiResponse> getCurrency() {
        CurrencyApiResponse currencyApiResponse = currencyService.getCurrencyFromApi();

        if (!currencyApiResponse.isSuccess()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(currencyApiResponse);
    }
}
