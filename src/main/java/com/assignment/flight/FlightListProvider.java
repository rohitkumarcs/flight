package com.assignment.flight;

import com.assignment.flight.cache.FlightRouteCache;
import com.assignment.flight.model.Flight;
import com.assignment.flight.model.FlightRoute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class FlightListProvider {

    private LinkedList<FlightRoute> routeLinkedList = new LinkedList<FlightRoute>(  );
    private LinkedHashMap<Integer, LinkedList<FlightRoute>> linkedHashMap = new LinkedHashMap(  );

    public void getListOfFlightsForRoute( String startingPoint, String endingPoint){
        List<Flight> flightList = FlightRouteCache.flightList;
//        List<FlightRoute> flightRoutesList = FlightRouteCache.flightRouteList;

        int stops = 0;
        List<FlightRoute> flightRoutesList = new ArrayList<FlightRoute>(  );
//        for( FlightRoute route : FlightRouteCache.flightRouteList ){
//            if( startingPoint.equalsIgnoreCase( route.getStartingPoint() ) ) {
//                if( endingPoint.equalsIgnoreCase( route.getEndingPoint()) ) {
//                    routeLinkedList.add( route );
//                    linkedHashMap.put( stops, routeLinkedList );
//                } else {
//
//                }
//
//            }
//
//            if( endingPoint.equalsIgnoreCase( route.getEndingPoint()) ) {
//
//            }
//            stops++;
//        }

    }
}
