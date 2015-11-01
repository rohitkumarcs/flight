package com.assignment.flight.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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
        List<List<String>> possibleRoutes = flightRouteCache.getLinkedRouteList( "c", "e" );
        Assert.assertTrue( possibleRoutes.size() == 2 );
        Assert.assertTrue( possibleRoutes.get( 0 ).size() == 1 );
        Assert.assertTrue( possibleRoutes.get( 1 ).size() == 2 );
        Assert.assertEquals( possibleRoutes.get( 0 ).get( 0 ), "c-e" );
        Assert.assertEquals( possibleRoutes.get( 1 ).get( 0 ), "c-d" );
        Assert.assertEquals( possibleRoutes.get( 1 ).get( 1 ), "d-e" );
    }
}
