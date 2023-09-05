package com.microservices.currencyconversionservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name="currency-exchange", url="localhost:8000") // döviz bozdurmaya url ile erişim sağladık
@FeignClient(name="currency-exchange") // sadece url kısmı kaldırılarak dengeleme işlemi yapılıyor
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    );

}
