package com.assignment.flight.cache;

import com.assignment.flight.Permutation;
import com.assignment.flight.model.Flight;
import com.assignment.flight.model.FlightRoute;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FlightRouteCache {

    private static String PROP_LOCATIONS = "com.assignment.loc.";
    private static String PROP_CONNECTIONS = "com.assignment.connecting.to.";
    private static String PROP_ROUTE_FLIGHTS = "com.assignment.route.";
    private static String PROP_FLIGHT = "com.assignment.flight";
    private static String PROP_NAME = ".name";
    private static String PROP_ROUTE_START = ".route.start";
    private static String PROP_ROUTE_END = ".route.end";
    public ArrayList<FlightRoute> flightRouteList = new ArrayList(  );
    public static ArrayList<Flight> flightList = new ArrayList(  );
    public HashMap<Integer, String> locationMap = new HashMap(  );
    public HashMap<String, List<String>> connectionMap = new HashMap(  );
    public HashMap<String, List<FlightRoute>> completeRoute = new HashMap(  );
    public HashMap<String, List<String> > flightRouteMap = new HashMap(  );
    public List<String> locationList = new ArrayList(  );

    public static HashMap<String, List<String>> routeLinksMap = new HashMap(  );

    public void load() throws IOException {
        Properties properties = new Properties();
        InputStream is = FlightRouteCache.class.getResourceAsStream( "flight-route.properties" );

        if(is != null)
        {
            properties.load(is);
        }

        getLocations( properties );
        getConnectedTo( properties );
        getCompleteRouteList();
    }

    private void getLocations( Properties properties){
        int index = 1;
        String location = null;
        do{
            location = (String) properties.get( PROP_LOCATIONS + index);
            if(location != null){
                locationMap.put( index, location );
                locationList.add( location );
            }
            index++;
        } while ( location != null );
    }

    private void getConnectedTo( Properties properties){
        int index = 1;
        String locations = null;
        do{
            locations = (String) properties.get( PROP_CONNECTIONS + index);
            if(locations != null){
                List<String> locationList = Arrays.asList( locations.split( "," ) );
                String startLocation = locationMap.get( index );
                connectionMap.put( startLocation, locationList );

                for(String endLocation : locationList){
                    int routeInx = 1;
                    String flight = null;
                    List<String> flightsList = new ArrayList<String>(  );
                    String key = startLocation + "-" + endLocation;
                    flightRouteMap.put( key, flightsList );
                    do{
                        flight = (String)properties.get( PROP_ROUTE_FLIGHTS + startLocation + "." + endLocation + "." + routeInx );
                        if( flight != null) {
                            flightsList.add( flight );
                        }
                        routeInx++;
                    } while ( flight != null );
                }
            }
            index++;
        } while ( locations != null );
    }

    private void getCompleteRouteList() {
        List<String> startLocations = new ArrayList<String>(locationMap.values());
        for(String location : startLocations) {
            String startLocation = location;
            for(String endLocation : startLocations) {
               if( endLocation != startLocation) {
                   String key = startLocation + "-" + endLocation;
                   List<FlightRoute> value = new ArrayList(  );

                   List<String> connectingLocations = connectionMap.get( startLocation );

                   completeRoute.put( key, value );
               }
            }

            String end = "";
        }
    }

    public void getLinkedRouteList( String startLocation, String endLocation){
        locationList.remove( startLocation );
        locationList.remove( endLocation );
        Permutation perm = new Permutation();

        List<List<String>> routesList =  perm.getRoutsBetweenLocations( locationList );
        List<List<String>> connectedRoutesList =  new ArrayList(  );
        String key = startLocation + "-" + endLocation;
        if( flightRouteMap.get( key ) != null) {
            connectedRoutesList.add( new ArrayList(Arrays.asList(startLocation, endLocation)) );
        }

        for(List<String> connectingLocations : routesList) {
            boolean isConnected = true;
            for ( int i =0; i < connectingLocations.size(); i++){
                if( connectingLocations.size() != 1 && connectingLocations.size() - 1 != i) {
                    key = connectingLocations.get( i ) + "-" + connectingLocations.get( i + 1 );
                    if( flightRouteMap.get( key ) == null) {
                        isConnected = false;
                    }
                } else if( connectingLocations.size() == 1) {
                    key = startLocation + "-" + connectingLocations.get( i );
                    if( flightRouteMap.get( key ) == null) {
                        isConnected = false;
                    } else {
                        key = connectingLocations.get( i ) + "-" + endLocation;
                        if( flightRouteMap.get( key ) == null) {
                            isConnected = false;
                        }
                    }
                } else if( connectingLocations.size() - 1 == i) {
                    key = connectingLocations.get( i ) + "-" + endLocation;
                    if( flightRouteMap.get( key ) == null) {
                        isConnected = false;
                    }
                }
                if( !isConnected )
                break;
            }
            if(isConnected)
                connectedRoutesList.add( connectingLocations );
        }

        System.out.println( connectedRoutesList );
    }


    public ArrayList<FlightRoute> getFlightRouteList() {
        return flightRouteList;
    }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public HashMap<Integer, String> getLocationMap() {
        return locationMap;
    }

    public HashMap<String, List<String>> getConnectionMap() {
        return connectionMap;
    }

    public static HashMap<String, List<String>> getRouteLinksMap() {
        return routeLinksMap;
    }
}
