package com.assignment.flight.cache;

import com.assignment.flight.FlightListProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FlightRouteCacheTest {

    private FlightRouteCache flightRouteCache;

    private FlightListProvider flightListProvider;

    @Before
    public void setup() throws IOException {
        flightRouteCache = new FlightRouteCache();
        flightRouteCache.load();
        flightListProvider = new FlightListProvider();
    }

    @Test
    public void testFlightRoute(){
        Assert.assertTrue( true );
        flightRouteCache.getLinkedRouteList( "c", "e" );
    }
}
