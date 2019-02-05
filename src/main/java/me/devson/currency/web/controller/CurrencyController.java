package me.devson.currency.web.controller;

import me.devson.currency.web.dto.CurrencyApiResponse;
import me.devson.currency.web.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableCaching
public class CurrencyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String currencyView() {
        return "currency";
    }

    @Cacheable(cacheNames = "apiCache")
    @GetMapping("/currency")
    @ResponseBody
    public ResponseEntity<CurrencyApiResponse> getCurrency() {
        LOGGER.debug("new cache was created");

        CurrencyApiResponse currencyApiResponse = currencyService.getCurrencyFromApi();

        if (!currencyApiResponse.isSuccess()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(currencyApiResponse);
    }
}