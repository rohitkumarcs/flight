package com.assignment.flight;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is intended to generate list of combination of connecting locations. E.G.
 * If list of location is b,c then it will return [[b], [c], [b,c], [c,b]]
 */
public class PossibleRoutePermutation {

    /**
     * @param locationList : This excludes start and end location as it is any way fixed at begining and end
     * @return
     */
    public List<List<String>> getRoutsBetweenLocations(List<String> locationList) {

        List<List<String>> totalList =  new ArrayList();
        for (int numberOfConnections = 1; numberOfConnections <= locationList.size(); numberOfConnections++) {
            totalList.addAll(getCombinationsPerLength( locationList, numberOfConnections ));
        }
        return totalList;

    }

    /**
     *
     * @param locationList
     * @param numberOfConnections this sends combination of location. Say for 2 locations combination can be of 1 and 2
     * @return
     */
    private ArrayList<List<String>> getCombinationsPerLength( List<String> locationList, int numberOfConnections ) {

        ArrayList<List<String>> combinations = new ArrayList();

        if (numberOfConnections == 1) {

            for (int j = 0; j < locationList.size(); j++) {
                List<String> nextList = new ArrayList(  );
                nextList.add( locationList.get( j ) );
                combinations.add( nextList );
            }
            return combinations;
        }
        for (int j = 0; j < locationList.size(); j++) {

            ArrayList<List<String>> combs = getCombinationsPerLength( locationList, numberOfConnections - 1 );
            for (List<String> list : combs) {
                if(!list.contains( locationList.get( j ) ) ){
                    List<String> next = new ArrayList(  );
                    next.add( locationList.get( j )  );
                    next.addAll( list );
                    combinations.add(next);
                }
            }
        }

        return combinations;
    }
}
