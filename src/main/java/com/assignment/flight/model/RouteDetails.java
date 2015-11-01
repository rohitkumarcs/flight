package com.assignment.flight.model;

import com.assignment.flight.cache.FlightRouteCache;
import com.sun.javafx.collections.transformation.SortedList;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class RouteDetails {

    private String startingPoint;
    private String endingPoint;

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint( String startingPoint ) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint( String endingPoint ) {
        this.endingPoint = endingPoint;
    }

    //    private LinkedList<FlightRoute> routeLinkedList = new LinkedList<FlightRoute>(  );
//
////    private LinkedHashMap<Integer, LinkedList<FlightRoute>> linkedHashMap = new LinkedHashMap(  );
//    private LinkedHashMap<String, LinkedList<FlightRoute>> linkedRoute = new LinkedHashMap(  );
//
//    public SortedList<LinkedList<FlightRoute>> getRouteDetails( String StartPoint, String endPoint) {
//        LinkedList<FlightRoute> routeList = new LinkedList<FlightRoute>(  );
//
//        FlightRouteCache cache = new FlightRouteCache();
//        List<FlightRoute> flightRoutes = cache.getFlightRouteList();
//        return null;
//    }
}
