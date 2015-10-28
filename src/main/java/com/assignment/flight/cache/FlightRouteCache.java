package com.assignment.flight.cache;

import com.assignment.flight.model.Flight;
import com.assignment.flight.model.FlightRoute;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class FlightRouteCache {

    private static String PROP_FLIGHT = "com.assignment.flight";
    private static String PROP_NAME = ".name";
    private static String PROP_ROUTE_START = ".route.start";
    private static String PROP_ROUTE_END = ".route.end";
    private ArrayList<FlightRoute> flightRouteList = new ArrayList(  );
    private ArrayList<Flight> flightList = new ArrayList(  );


    public void load() throws IOException {
        Properties pomProperties = new Properties();
        InputStream is = FlightRouteCache.class.getResourceAsStream( "flight-route.properties" );

        if(is != null)
        {
            pomProperties.load(is);
        }

        int index = 1;
        String flightName = null;
        do{
            flightName = (String) pomProperties.get( PROP_FLIGHT + index++ + PROP_NAME );
            if(flightName != null){
                String flightRouteStart = (String) pomProperties.get( PROP_FLIGHT + index++ + PROP_ROUTE_START );
                String flightRouteEnd = (String) pomProperties.get( PROP_FLIGHT + index++ + PROP_ROUTE_END );
                FlightRoute route = new FlightRoute( flightRouteStart, flightRouteEnd);
                Flight flight = new Flight( flightName, route);
                flightRouteList.add( route );
                flightList.add( flight );
            }
            System.out.println( flightName );
        }while ( flightName != null );

    }
}
