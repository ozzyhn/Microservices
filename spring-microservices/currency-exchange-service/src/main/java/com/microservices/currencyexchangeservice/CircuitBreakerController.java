package com.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {


 private Logger logger = LoggerFactory
         .getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    //@Retry(name="default" , fallbackMethod = "fallbackResponse")
   // @CircuitBreaker(name="default" , fallbackMethod = "fallbackResponse")
    //@RateLimiter(name="default")
    @Bulkhead(name="default")
    public String sampleApi() {
        logger.info("Sample Api call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost8000", String.class);
        return "sample API";
    }
    public String fallbackResponse(Exception exception) {
        return "fallback-response";
    }
}
