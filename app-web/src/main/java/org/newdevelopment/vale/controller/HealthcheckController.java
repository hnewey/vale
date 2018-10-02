package org.newdevelopment.vale.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "healthcheck" )
public class HealthcheckController {

    private static final Logger LOGGER = LoggerFactory.getLogger( HealthcheckController.class );

    private static final String FULLSERVICENAME = "Vale Application";


    @Autowired
    public HealthcheckController() {
        LOGGER.info( "{} initialized ({})", this.getClass().getSimpleName(), FULLSERVICENAME );
    }

    /**
     *  Simple heartbeat (ping) endpoint.  Returns a hard-code string that
     *  indicates to an HTTP client that the Vale service is operational.</br>
     *
     *  @return  Successful heartbeat status (200)
     *
     */
    @RequestMapping( value = "/heartbeat", method = RequestMethod.GET )
    public ResponseEntity doHeartbeat() {
        return ResponseEntity.ok(FULLSERVICENAME);
    }

}
