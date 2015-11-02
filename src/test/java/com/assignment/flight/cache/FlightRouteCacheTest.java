package com.assignment.flight.cache;

import com.assignment.flight.data.StartToEndRouteDetails;
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

        List<List<String>> possibleRoutes = flightRouteCache.getLinkedRouteList( "c", "e" );
        Assert.assertTrue( possibleRoutes.size() == 2 );
        Assert.assertTrue( possibleRoutes.get( 0 ).size() == 1 );
        Assert.assertTrue( possibleRoutes.get( 1 ).size() == 2 );
        Assert.assertEquals( possibleRoutes.get( 0 ).get( 0 ), "c-e" );
        Assert.assertEquals( possibleRoutes.get( 1 ).get( 0 ), "c-d" );
        Assert.assertEquals( possibleRoutes.get( 1 ).get( 1 ), "d-e" );

        List<StartToEndRouteDetails> completeRouteList = flightRouteCache.getRouteDetails( "c", "e" );
        Assert.assertTrue( completeRouteList.size() == 2 );
        Assert.assertEquals( completeRouteList.get( 0 ).toString(),
                             "[5 Flights [AI004, AI005, AI006, AI007, AI008] fly from c to e] with 0 stops." );
        Assert.assertEquals( completeRouteList.get( 1 ).toString(),
                             "[1 Flights [AI009] fly from c to d, 1 Flights [AI010] fly from d to e] with 1 stops." );
    }
}
