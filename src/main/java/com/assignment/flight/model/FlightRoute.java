package com.assignment.flight.model;

public class FlightRoute {

    private String startingPoint;
    private String endingPoint;

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
}
