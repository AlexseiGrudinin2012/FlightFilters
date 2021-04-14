package com.gridnine.testing.filters;

import com.gridnine.testing.flight.Flight;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface FiltersImplement {

    static void showAllFlight(List<Flight> flightList, String title) {
        System.out.println(title);
        showAllFlight(flightList);

    }

    static void showAllFlight(List<Flight> flightList) {
        flightList.forEach(f ->
                {
                    System.out.println("flight number - " + f.getIdFlight());
                    AtomicInteger segment = new AtomicInteger(0);
                    f.getSegments().forEach(s ->
                            System.out.println("\t" + s + " Segment number is " + segment.incrementAndGet())
                    );
                    System.out.println();
                }
        );
    }

    List<Flight> getDepartureTimeBeforeNow(); //фильтр по времени ранее чем сейчас

    List<Flight> getArrivalDateLessDepartureDate(); //  не вылетел, но уже приземлился

    List<Flight> getFlightWithTransferMoreThanTwoHours(); //простой более 2х часов


}
