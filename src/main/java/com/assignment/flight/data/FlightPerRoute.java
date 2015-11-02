package com.assignment.flight.data;

import java.util.ArrayList;
import java.util.List;

/**
 * List down all the direct flights between two location
 */
public class FlightPerRoute {
    private String startPoint;
    private String endPoint;
    private List<String> flights = new ArrayList(  );

    public FlightPerRoute( String startPoint, String endPoint ) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void addFlights( String flight ) {
        this.flights.add( flight );
    }

    @Override
    public String toString() {
        return flights.size() + " Flights " + flights + " fly from " + startPoint + " to " + endPoint;
    }
}
