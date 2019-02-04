package me.devson.currency.web.service;

import me.devson.currency.web.dto.CurrencyApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    private static final String CURRENCY_API_URL = "http://www.apilayer.net/api/live";
    private final String apiQueryString;

    public CurrencyService(@Value("${api-key}") String apiKey) {
        this.apiQueryString = "?source=USD&currencies=KRW,PHP,JPY&access_key=" + apiKey;
    }

    public CurrencyApiResponse getCurrencyFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(CURRENCY_API_URL + apiQueryString, CurrencyApiResponse.class);
    }
}
