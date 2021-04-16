package com.gridnine.testing.filters;

import com.gridnine.testing.flight.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class DepartureTimeBeforeNow extends Filter {

    public DepartureTimeBeforeNow(List<Flight> flightList) {
        super(flightList);
    }

    @Override
    public List<Flight> checkDump() {
        AtomicBoolean isSearchToResult = new AtomicBoolean();
        getFlightList().forEach(
                f -> {
                    isSearchToResult.set(false);
                    f.getSegments()
                            .forEach(
                                    s ->
                                    {
                                        LocalDateTime departureDate = s.getDepartureDate();
                                        if (departureDate.isBefore(LocalDateTime.now()) && !isSearchToResult.get()) {
                                            isSearchToResult.set(true);
                                            setResult(f);

                                        }

                                    }
                            );
                }
        );
        return getResult();
    }
}
