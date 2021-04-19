package com.gridnine.testing.filters;

import com.gridnine.testing.filters.parents.FiltersImplement;
import com.gridnine.testing.flight.Flight;

import java.util.ArrayList;
import java.util.List;


public class DumpFilters {

    public List<Flight> getFlightList() {
        return flightList;
    }

    private final List<FiltersImplement> filtersImplementList;
    private final List<Flight> flightList;


    public List<FiltersImplement> getFiltersImplementList() {
        return filtersImplementList;
    }


    public DumpFilters(List<Flight> flightList) {
        this.flightList = flightList;
        filtersImplementList = new ArrayList<>();
        filtersImplementList.add(new DepartureTimeBeforeNow(flightList));
        filtersImplementList.add(new ArrivalDateLessDepartureDateFilter(flightList));
        filtersImplementList.add(new FlightForParkingWithTimeAFilter(flightList));
    }

    public <T> FiltersImplement getFilter(Class<T> clazz) {

        return filtersImplementList.stream()
                .filter(filter -> clazz.isAssignableFrom(filter.getClass()))
                .findFirst()
                .orElse(null);
    }


}
