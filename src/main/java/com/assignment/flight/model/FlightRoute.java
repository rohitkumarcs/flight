package com.assignment.flight.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRoute {

    private String startingPoint;
    private String endingPoint;
    private List<String> flightsList = new ArrayList<String>(  );



    public FlightRoute( String startingPoint, String endingPoint ) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

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

    public List<String> getFlightsList() {
        return flightsList;
    }

    public void setFlightsList( List<String> flightsList ) {
        this.flightsList = flightsList;
    }

    public void addFlight( String flight){
        flightsList.add( flight );
    }

    public int getNumberOfFlights(){
        return flightsList.size();
    }
}
