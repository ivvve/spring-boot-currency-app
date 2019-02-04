package me.devson.currency.web.controller;

import me.devson.currency.web.dto.CurrencyApiResponse;
import me.devson.currency.web.service.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private CurrencyService currencyService;

    @Test
    public void 환율_view_테스트() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("currency"));
    }

    @Test
    public void get_ajax_환율_성공_테스트() throws Exception {
        CurrencyApiResponse apiResponse = new CurrencyApiResponse();
        apiResponse.setSuccess(true);

        when(currencyService.getCurrencyFromApi()).thenReturn(apiResponse);

        mockMvc.perform(get("/currency"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void get_ajax_환율_실패_테스트() throws Exception {
        CurrencyApiResponse apiResponse = new CurrencyApiResponse();
        apiResponse.setSuccess(false);

        when(currencyService.getCurrencyFromApi()).thenReturn(apiResponse);

        mockMvc.perform(get("/currency"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
