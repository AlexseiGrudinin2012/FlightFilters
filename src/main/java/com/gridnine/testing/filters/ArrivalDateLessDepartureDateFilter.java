package com.gridnine.testing.filters;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalDateLessDepartureDateFilter extends Filter {

    public ArrivalDateLessDepartureDateFilter(List<Flight> flightList) {
        super(flightList);
    }

    @Override
    public List<Flight> checkDump() {

        getFlightList().forEach(
                f -> {
                    var segments = f.getSegments();
                    Segment lastSegment = segments.get(0);

                    if (lastSegment.getArrivalDate().isBefore(lastSegment.getDepartureDate())) {
                        setResult(f);
                    } else {

                        for (int i = 1; i < segments.size(); i++) {
                            LocalDateTime arrivalDate = segments.get(i).getArrivalDate();
                            LocalDateTime departureDate = segments.get(i).getDepartureDate();

                            if (
                                    arrivalDate.isBefore(departureDate) ||
                                            lastSegment.getArrivalDate().isAfter(departureDate)
                            ) {

                                setResult(f);
                                break;
                            }
                            lastSegment = segments.get(i);
                        }
                    }
                }
        );

        return getResult();

    }
}
