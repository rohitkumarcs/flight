package com.assignment.flight.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FlightRouteCacheTest {

    private FlightRouteCache flightRouteCache;

    @Before
    public void setup() throws IOException {
        flightRouteCache = new FlightRouteCache();
        flightRouteCache.load();
    }

    @Test
    public void testFlightRoute(){
        Assert.assertTrue( true );
    }
}
