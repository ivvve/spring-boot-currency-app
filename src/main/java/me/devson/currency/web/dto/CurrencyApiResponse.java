package me.devson.currency.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyApiResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private Long timestamp;
    private String source;
    private Map<String, String> quotes;
}
