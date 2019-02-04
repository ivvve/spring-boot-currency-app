package me.devson.currency.web.controller;

import me.devson.currency.web.dto.CurrencyApiResponse;
import me.devson.currency.web.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String currencyView() {
        return "currency";
    }

    @GetMapping("/currency")
    @ResponseBody
    public ResponseEntity<CurrencyApiResponse> getCurrency() {
        CurrencyApiResponse currencyApiResponse = currencyService.getCurrencyFromApi();

        if (!currencyApiResponse.isSuccess()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(currencyApiResponse);
    }
}
