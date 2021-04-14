package com.gridnine.testing.filters;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Filters implements FiltersImplement {

    private final List<Flight> flightList;
    AtomicBoolean isSearchToResult = new AtomicBoolean();

    private List<Flight> getFlightList() {
        return flightList;
    }

    public Filters(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getArrivalDateLessDepartureDate() {
        //ДАТА ВЫЛЕТА ПОЗЖЕ ЧЕМ ДАТА ПРИЛЕТА
        List<Flight> result = new ArrayList<>();
        getFlightList().forEach(
                f -> {
                    var segments = f.getSegments();
                    Segment lastSegment = segments.get(0);

                    if (lastSegment.getArrivalDate().isBefore(lastSegment.getDepartureDate())) {
                        result.add(f);
                    } else {

                        for (int i = 1; i < segments.size(); i++) {
                            LocalDateTime arrivalDate = segments.get(i).getArrivalDate();
                            LocalDateTime departureDate = segments.get(i).getDepartureDate();

                            if (
                                    arrivalDate.isBefore(departureDate) ||
                                            lastSegment.getArrivalDate().isAfter(departureDate)
                            ) {

                                result.add(f);
                                break;
                            }
                            lastSegment = segments.get(i);
                        }
                    }
                }


        );

        return result;
    }


    public List<Flight> getDepartureTimeBeforeNow() {
        // ВЫЛЕТ до ТЕКУЩЕГО ВРЕМЕНИ
        List<Flight> result = new ArrayList<>();


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
                                            result.add(f);

                                        }

                                    }
                            );
                }
        );
        return result;
    }

    public List<Flight> getFlightWithTransferMoreThanTwoHours() {
        List<Flight> result = new ArrayList<>();

        getFlightList().forEach(
                f ->
                {
                    var segments = f.getSegments();

                    Segment segmentTmp = segments.get(0);

                    long differenceSeconds = 0;
                    if (segments.size() > 1) {

                        for (int i = 1; i < segments.size(); i++) {
                            LocalDateTime arrivalDate = segmentTmp.getArrivalDate();
                            LocalDateTime departureDate = segments.get(i).getDepartureDate();
                            differenceSeconds += Duration.between(arrivalDate, departureDate).toSeconds();
                            segmentTmp = segments.get(i);
                        }

                        if (differenceSeconds > 7200) {
                            result.add(f);
                        }

                    }
                }
        );

        return result;
    }


    public Flight getFlight(Integer idFlight) {
        int size = getFlightList().size();

        if (size == 1) {
            return getFlightList().get(0);
        }

        Flight flight;

        for (int i = 0; i < size; i++) {
            flight = getFlightList().get(i);

            if (flight.getIdFlight().equals(idFlight)) {
                return flight;
            }

        }

        return null;

    }

}
