package com.assignment.flight.cache;

import com.assignment.flight.PossibleRoutePermutation;
import com.assignment.flight.model.FlightPerRoute;
import com.assignment.flight.StartToEndRouteDetails;

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
    private HashMap<Integer, String> locationMap = new HashMap(  );
    private HashMap<String, FlightPerRoute> flightRouteMap = new HashMap(  );

    /**
     * Since we are not using DB for storing our data. We are using Property file to store them.
     * This method will read property file and populate LocationMap and FlightRouteMap
     * @throws IOException
     */
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

    /**
     * @param startLocation
     * @param endLocation
     * @return List of connecting routes between two location in a proper format.
     * The purpose is to make it understandable and close to what we would get in UI.
     */
    public List<StartToEndRouteDetails> getRouteDetails( String startLocation, String endLocation ) {
        List<List<String>> routeDetails = getLinkedRouteList( startLocation, endLocation);
        List<StartToEndRouteDetails> completeRouteList = new ArrayList(  );
        for( List<String> routes : routeDetails) {
            StartToEndRouteDetails startToEndRouteDetails = new StartToEndRouteDetails( startLocation, endLocation );
            for ( String route : routes ){
                FlightPerRoute flightPerRoute = flightRouteMap.get( route );
                startToEndRouteDetails.addFlightPerRoute( flightPerRoute );
            }
            completeRouteList.add( startToEndRouteDetails );
        }
        System.out.println( completeRouteList );
        return completeRouteList;
    }

    /**
     * @param startLocation
     * @param endLocation
     * @return List of connecting routes between two location order by stops.
     */
    public List<List<String>> getLinkedRouteList( String startLocation, String endLocation){
        List<String> locationList = new ArrayList<String>(locationMap.values());
        locationList.remove( startLocation );
        locationList.remove( endLocation );
        PossibleRoutePermutation perm = new PossibleRoutePermutation();
        List<List<String>> connectedRoutesList =  new ArrayList(  );
        String key = startLocation + "-" + endLocation;
        if( flightRouteMap.get( key ) != null) {
            connectedRoutesList.add(Arrays.asList(startLocation + "-" + endLocation));
        }

        for(List<String> connectingLocations : perm.getRoutsBetweenLocations( locationList )) {
            boolean isConnected = true;
            List<String> routes = new ArrayList(  );
            for ( int i =0; i < connectingLocations.size(); i++){
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


        return connectedRoutesList;
    }

    /**
     * This populate Location Map with index considered as primary key for each location.
     * @param properties
     */
    private void getLocations( Properties properties){
        int index = 1;
        String location;
        do{
            location = (String) properties.get( PROP_LOCATIONS + index);
            if(location != null){
                locationMap.put( index, location );
            }
            index++;
        } while ( location != null );
    }

    /**
     * This list down all direct connection between two route like. if location a to b has direct connection then
     * key = a-b nad value = FlightPerRoute.class
     * @param properties
     */
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
                    String key = startLocation + "-" + endLocation;
                    FlightPerRoute flightPerRoute = new FlightPerRoute( startLocation, endLocation );
                    flightRouteMap.put( key, flightPerRoute );
                    do{
                        flight = (String)properties.get( PROP_ROUTE_FLIGHTS + startLocation + "." + endLocation + "." + routeInx );
                        if( flight != null) {
                            flightPerRoute.addFlights( flight );
                        }
                        routeInx++;
                    } while ( flight != null );
                }
            }
            index++;
        } while ( locations != null );
    }

    /**
     * This method identifies if there is direct connection between two routes
     * @param startLocation
     * @param endLocation
     * @return
     */
    private boolean isConnected( String startLocation, String endLocation ) {
        String key = startLocation + "-" + endLocation;
        return flightRouteMap.get( key ) != null;
    }
}