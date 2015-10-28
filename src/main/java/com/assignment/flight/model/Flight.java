package com.assignment.flight.model;

public class Flight {

    private String flightName;
    private FlightRoute flightRoute;

    public Flight( String flightName, FlightRoute flightRoute ) {
        this.flightName = flightName;
        this.flightRoute = flightRoute;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName( String flightName ) {
        this.flightName = flightName;
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute( FlightRoute flightRoute ) {
        this.flightRoute = flightRoute;
    }
}
