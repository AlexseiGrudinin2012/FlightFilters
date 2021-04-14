package com.gridnine.testing;

import com.gridnine.testing.filters.Filters;
import com.gridnine.testing.filters.FiltersImplement;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        Filters filters = new Filters(flights);

        var filter1 = filters.getDepartureTimeBeforeNow();
        var filter2 = filters.getArrivalDateLessDepartureDate();
        var filter3 = filters.getFlightWithTransferMoreThanTwoHours();

        FiltersImplement.showAllFlight(filter1, "Вылет до текущего времени");
        FiltersImplement.showAllFlight(filter2, "Дата приземления раньше чем дата вылета");
        FiltersImplement.showAllFlight(filter3, "общее время нахождения на земле более 2-х часов");


    }
}
