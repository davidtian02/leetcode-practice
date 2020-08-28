package com.leetcode.problems.medium.design_underground_system;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/design-underground-system/submissions/
class UndergroundSystem {
    /**
     * TODO should use the concept of "Route". fits pretty well for this problem.
     *
     * why does a system need to know all passengers between ALL stations? hmm...
     *
     * this aims for OOP rather than performance.
     */
    private Map<Integer, Passenger> travellingPassengers;
    private Map<String, Station> stations;
    public UndergroundSystem() {
        travellingPassengers = new HashMap<>();
        stations = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        final Station station;
        if (!stations.containsKey(stationName)) {
            station = new Station(stationName);
            stations.put(stationName, station);
        } else {
            station = stations.get(stationName);
        }
        travellingPassengers.put(id, new Passenger(id, station, t)); // assume can't check in if already in
    }

    public void checkOut(int id, String stationName, int t) {
        final Station destination;
        if (!stations.containsKey(stationName)) {
            destination = new Station(stationName);
            stations.put(stationName, destination);
        } else {
            destination = stations.get(stationName);
        }
        Passenger p = travellingPassengers.get(id);
        Station start = p.startStation;
        if (!start.outboundStatistics.containsKey(destination)) {
            Stat stat = new Stat();
            stat.sumDurations = t - p.t1;
            stat.tripCount = 1;
            start.outboundStatistics.put(destination, stat);
        } else {
            Stat stat = start.outboundStatistics.get(destination);
            stat.sumDurations += t - p.t1;
            stat.tripCount++;
        }
        travellingPassengers.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        Station origin = stations.get(startStation);
        Station destination = stations.get(endStation);
        Stat stat = origin.outboundStatistics.get(destination);
        return (stat.sumDurations/(0.0+stat.tripCount));
    }

    static class Passenger {
        int id, t1;
        Station startStation;
        Passenger(int i, Station s, int t) {
            id = i;
            t1 = t;
            startStation = s;
        }
    }
    static class Station {
        String name;
        Map<Station, Stat> outboundStatistics;
        Station(String s) {
            name = s;
            outboundStatistics = new HashMap<>();
        }
    }
    static class Stat {
        long sumDurations, tripCount; //stats
    }

    public static void main(String... args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));       // return 14.00000. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.00000. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.00000
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 12.00000
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */