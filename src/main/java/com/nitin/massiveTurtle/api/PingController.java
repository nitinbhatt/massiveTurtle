package com.nitin.massiveTurtle.api;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PingController {

    @QueryMapping
    public String ping() {
        return "pong";
    }
}
