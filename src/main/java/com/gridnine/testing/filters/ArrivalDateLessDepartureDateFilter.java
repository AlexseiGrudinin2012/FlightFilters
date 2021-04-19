package com.gridnine.testing.filters;

import com.gridnine.testing.filters.parents.Filter;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalDateLessDepartureDateFilter extends Filter {

    public ArrivalDateLessDepartureDateFilter(List<Flight> flightList) {
        super(flightList);
    }

    public List<Flight> checkDump() {

        for (Flight flight : getFlightList()) {
            List<Segment> segments = flight.getSegments();
            Segment lastSegment = segments.get(0);

            if (lastSegment.getArrivalDate().isBefore(lastSegment.getDepartureDate())) {
                setResult(flight);
            } else {
                for (int i = 1; i < segments.size(); i++) {
                    LocalDateTime arrivalDate = segments.get(i).getArrivalDate();
                    LocalDateTime departureDate = segments.get(i).getDepartureDate();

                    if (arrivalDate.isBefore(departureDate) ||
                            lastSegment.getArrivalDate().isAfter(departureDate)) {
                        setResult(flight);
                        break;
                    }
                    lastSegment = segments.get(i);
                }
            }
        }
        return getResult();
    }
}
