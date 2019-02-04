package me.devson.currency.web.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class CurrencyApiResponse {
    private Boolean success;
    private String terms;
    private String privacy;
    private Long timestamp;
    private String source;
    private Map<String, String> quotes;
}
