package com.assignment.flight.cache;

import com.assignment.flight.PossibleRoutePermutation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class FlightRouteCache {

    private static String PROP_LOCATIONS = "com.assignment.loc.";
    private static String PROP_CONNECTIONS = "com.assignment.connecting.to.";
    private static String PROP_ROUTE_FLIGHTS = "com.assignment.route.";
    public HashMap<Integer, String> locationMap = new HashMap(  );
    public HashMap<String, List<String> > flightRouteMap = new HashMap(  );
    public List<String> locationList = new ArrayList(  );

    public void load() throws IOException {
        Properties properties = new Properties();
        InputStream is = FlightRouteCache.class.getResourceAsStream( "flight-route.properties" );

        if(is != null)
        {
            properties.load(is);
        }

        getLocations( properties );
        getConnectedTo( properties );
    }

    public List<List<String>> getLinkedRouteList( String startLocation, String endLocation){
        locationList.remove( startLocation );
        locationList.remove( endLocation );
        PossibleRoutePermutation perm = new PossibleRoutePermutation();

        List<List<String>> routesList =  perm.getRoutsBetweenLocations( locationList );
        List<List<String>> connectedRoutesList =  new ArrayList(  );
        String key = startLocation + "-" + endLocation;
        if( flightRouteMap.get( key ) != null) {
            connectedRoutesList.add( new ArrayList(Arrays.asList(startLocation + "-" + endLocation)) );
        }

        for(List<String> connectingLocations : routesList) {
            boolean isConnected = true;
            List<String> routes = new ArrayList(  );
            for ( int i =0; i < connectingLocations.size(); i++){
                String route;
                if( connectingLocations.size() != 1 && connectingLocations.size() - 1 != i) {
                    isConnected = isConnected( connectingLocations.get( i ), connectingLocations.get( i + 1 ) );
                    routes.add(connectingLocations.get( i ) + "-" + connectingLocations.get( i + 1 ));
                } else if( connectingLocations.size() == 1) {
                    isConnected = isConnected( startLocation, connectingLocations.get( i ) ) &&
                        isConnected( connectingLocations.get( i ), endLocation );
                    routes.add(startLocation + "-" + connectingLocations.get( i ));
                    routes.add(connectingLocations.get( i ) + "-" + endLocation);
                } else if( connectingLocations.size() - 1 == i) {
                    isConnected = isConnected( connectingLocations.get( i ), endLocation );
                    routes.add(connectingLocations.get( i ) + "-" + endLocation);
                }
                if( !isConnected ){
                    break;
                }
            }
            if(isConnected){
                connectedRoutesList.add( routes );
            }
        }

        Collections.sort(connectedRoutesList, new Comparator<List<String>>( ) {

            @Override
            public int compare(List<String> i1, List<String> i2) {
                return (i2.size() < i1.size()) ? 1 : -1;
            }
        });

        System.out.println( connectedRoutesList );

        return connectedRoutesList;
    }

    private void getLocations( Properties properties){
        int index = 1;
        String location;
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
        String locations;
        do{
            locations = (String) properties.get( PROP_CONNECTIONS + index);
            if(locations != null){
                List<String> locationList = Arrays.asList( locations.split( "," ) );
                String startLocation = locationMap.get( index );
                for(String endLocation : locationList){
                    int routeInx = 1;
                    String flight;
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

    private boolean isConnected( String startLocation, String endLocation ) {
        String key = startLocation + "-" + endLocation;
        return flightRouteMap.get( key ) != null;
    }

    public HashMap<String, List<String>> getFlightRouteMap() {
        return flightRouteMap;
    }
}