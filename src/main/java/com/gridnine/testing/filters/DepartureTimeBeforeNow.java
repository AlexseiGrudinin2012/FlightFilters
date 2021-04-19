package com.gridnine.testing.filters;

import com.gridnine.testing.filters.parents.Filter;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class DepartureTimeBeforeNow extends Filter {

    public DepartureTimeBeforeNow(List<Flight> flightList) {
        super(flightList);
    }

    public List<Flight> checkDump() {


        AtomicBoolean isSearchToResult = new AtomicBoolean();
        for (Flight flight : getFlightList()) {
            isSearchToResult.set(false);

            for (Segment segment : flight.getSegments()) {

                LocalDateTime departureDate = segment.getDepartureDate();
                if (departureDate.isBefore(LocalDateTime.now()) && !isSearchToResult.get()) {
                    isSearchToResult.set(true);
                    setResult(flight);
                }
            }
        }
        return getResult();

    }
}
