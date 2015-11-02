package com.assignment.flight.data;

import java.util.ArrayList;
import java.util.List;

/**
 * List down one complete route between start and end Destination
 */
public class StartToEndRouteDetails {
    private String startingDestination;
    private String endDestination;
    private List<FlightPerRoute> completeRouteList = new ArrayList(  );

    public StartToEndRouteDetails( String startingDestination, String endDestination ) {
        this.startingDestination = startingDestination;
        this.endDestination = endDestination;
    }

    public void addFlightPerRoute( FlightPerRoute flightPerRoute){
        completeRouteList.add( flightPerRoute );
    }

    @Override
    public String toString() {
        return completeRouteList.toString() + " with " + (completeRouteList.size() - 1) + " stops.";
    }
}
