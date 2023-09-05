package com.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    private CurrencyExchangeRepository repository;
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {

        logger.info("retrieveExchangeValue called with{} to {}",from,to);
        // veri tabanından veriyi çekiyoruz
        CurrencyExchange currencyExchange
                =repository.findByFromAndTo(from, to);

        // sınıfın içerisine tanımladıgımız Environment nesnesinin oluşturduğumuz set metodunu kullanıyoruz
        if(currencyExchange==null){
            throw new RuntimeException
                    ("Unable Find Data for"+from+"to"+to);
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;

    }
}
