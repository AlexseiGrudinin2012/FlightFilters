package com.gridnine.testing.filters;

import com.gridnine.testing.filters.parents.Filter;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightForParkingWithTimeAFilter extends Filter {


    private Integer seconds = 7200;


    public FlightForParkingWithTimeAFilter(List<Flight> flightList) {
        super(flightList);
    }

    public FlightForParkingWithTimeAFilter(List<Flight> flightList, Integer hour) {
        this(flightList);
        setHour(hour);
    }


    private Integer getSeconds() {
        return seconds;
    }

    public void setHour(Integer hour) {
        this.seconds = hour * 3600;
    }


    public List<Flight> checkDump() {

        for (Flight flight : getFlightList()) {
            List<Segment> segments = flight.getSegments();
            Segment segmentTmp = segments.get(0);
            long differenceSeconds = 0;

            if (segments.size() > 1) {
                for (int i = 1; i < segments.size(); i++) {
                    LocalDateTime arrivalDate = segmentTmp.getArrivalDate();
                    LocalDateTime departureDate = segments.get(i).getDepartureDate();
                    differenceSeconds += Duration.between(arrivalDate, departureDate).toSeconds();
                    segmentTmp = segments.get(i);
                }

                if (differenceSeconds > getSeconds()) {
                    setResult(flight);
                }
            }
        }
       return getResult();
    }


}
