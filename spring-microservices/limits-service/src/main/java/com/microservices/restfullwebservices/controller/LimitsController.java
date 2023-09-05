package com.microservices.restfullwebservices.controller;

import com.microservices.restfullwebservices.Bean.Limits;
import com.microservices.restfullwebservices.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {


    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(),
                configuration.getMaximum());
//		return new Limits(1,1000);
    }
}

