package com.assignment.flight;

import java.util.ArrayList;
import java.util.List;

public class PossibleRoutePermutation {

    public List<List<String>> getRoutsBetweenLocations(List<String> locationList) {

        List<List<String>> totalList =  new ArrayList();
        for (int i = 1; i <= locationList.size(); i++) {
            totalList.addAll(getCombinationsPerLength( locationList, i ));
        }
        return totalList;

    }

    private static ArrayList<List<String>> getCombinationsPerLength( List<String> locationList, int i ) {

        ArrayList<List<String>> combinations = new ArrayList();

        if (i == 1) {

            for (int j = 0; j < locationList.size(); j++) {
                List<String> nextList = new ArrayList(  );
                nextList.add( locationList.get( j ) );
                combinations.add( nextList );
            }
            return combinations;
        }
        for (int j = 0; j < locationList.size(); j++) {

            ArrayList<List<String>> combs = getCombinationsPerLength( locationList, i - 1 );
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

//    public static void main(String args[]) {
//        ArrayList testList = new ArrayList(Arrays.asList("a", "b", "c"));;
//        System.out.println(getRoutsBetweenLocations( testList ));
//    }
}
