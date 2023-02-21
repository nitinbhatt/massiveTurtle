package com.nitin.massiveTurtle.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class PingController {

    /**
     * Ping controller to check if server is up and running.
     * @return : Send a PONG back to client
     */
    @QueryMapping
    public String ping() {
        log.info("Calling PING");
        return "pong";
    }
}
