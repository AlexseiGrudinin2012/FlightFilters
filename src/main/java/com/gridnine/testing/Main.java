package com.gridnine.testing;

import com.gridnine.testing.filters.*;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        demonstrationToOneClass(flights, ArrivalDateLessDepartureDateFilter.class, "Дата вылета позже чем дата прилета");
        demonstrationToOneClass(flights, DepartureTimeBeforeNow.class, "Вылет до текущего времени");
        demonstrationToOneClass(flights, FlightForParkingWithTimeAFilter.class, "Общее время стоянки - 2 часа");

        demonstrationAnyClass(flights);
        //or

        FiltersImplement filtersImplement = new FlightForParkingWithTimeAFilter(flights, 3);
        filtersImplement.showList(filtersImplement.checkDump());

    }


    public static <T> void demonstrationToOneClass(List<Flight> flightList, Class<T> clazz, String title) {
        DumpFilters dumpFilters = new DumpFilters(flightList);
        var filter = dumpFilters.getFilter(clazz);
        filter.checkDump();
        filter.showFilterListResult(title);
    }

    public static void demonstrationAnyClass(List<Flight> flightList) {
        DumpFilters dumpFilters = new DumpFilters(flightList);

        dumpFilters.getFiltersImplementList().forEach(
                imp ->
                {
                    imp.checkDump();
                    imp.showFilterListResult();
                }
        );

    }
}
