package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalDateLessDepartureDateFilter;
import com.gridnine.testing.filters.DepartureTimeBeforeNow;
import com.gridnine.testing.filters.DumpFilters;
import com.gridnine.testing.filters.FlightForParkingWithTimeAFilter;
import com.gridnine.testing.filters.parents.FiltersImplement;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.FlightBuilder;

import java.util.List;

public class Main {

    final static String MESSAGE_ARRIVAL = "Дата вылета позже чем дата прилета";
    final static String MESSAGE_DEPARTURE = "Вылет до текущего времени";
    final static String FLIGHT_FOR_PARKING = "Общее время стоянки - 2 часа";


    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        final Boolean SHOW_COUNT_RECORD = true;

        demonstrationToOneClass(flights, ArrivalDateLessDepartureDateFilter.class, MESSAGE_ARRIVAL);
        demonstrationToOneClass(flights, DepartureTimeBeforeNow.class, MESSAGE_DEPARTURE);
        demonstrationToOneClass(flights, FlightForParkingWithTimeAFilter.class, FLIGHT_FOR_PARKING);

        demonstrationAnyClass(flights);
        //or

        FiltersImplement filtersImplement = new FlightForParkingWithTimeAFilter(flights, 3);

        filtersImplement.showList(filtersImplement.checkDump(),SHOW_COUNT_RECORD);
    }


    public static <T> void demonstrationToOneClass(List<Flight> flightList, Class<T> clazz, String title) {
        DumpFilters dumpFilters = new DumpFilters(flightList);
        FiltersImplement filter = dumpFilters.getFilter(clazz);
        filter.checkDump();
        filter.showFilterListResult(title);
    }

    public static void demonstrationAnyClass(List<Flight> flightList) {
        DumpFilters dumpFilters = new DumpFilters(flightList);

        List<FiltersImplement> filtersImplementList = dumpFilters.getFiltersImplementList();

        filtersImplementList.forEach(
                imp ->
                {
                    imp.checkDump();
                    imp.showFilterListResult();
                }
        );

    }
}
