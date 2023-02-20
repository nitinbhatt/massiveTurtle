package com.nitin.massiveTurtle.api;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PingController {

    /**
     * Ping controller to check if server is up and running.
     * @return : Send a PONG back to client
     */
    @QueryMapping
    public String ping() {
        return "pong";
    }
}
