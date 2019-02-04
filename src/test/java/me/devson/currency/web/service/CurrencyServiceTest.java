package me.devson.currency.web.service;

import me.devson.currency.web.dto.CurrencyApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    private CurrencyService currencyService = new CurrencyService("apikey");

    @Test
    public void api_정상_호출_테스트() {
        CurrencyApiResponse response = currencyService.getCurrencyFromApi();
        assertThat(response.getSuccess(), is(true));
        assertThat(response.getSource(), is("USD"));
        assertThat(response.getQuotes().keySet(), hasItems("USDKRW", "USDPHP", "USDJPY"));
    }
}
