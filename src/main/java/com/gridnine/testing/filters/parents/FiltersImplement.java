package com.gridnine.testing.filters.parents;

import com.gridnine.testing.flight.Flight;

import java.util.List;


public interface FiltersImplement {
    List<Flight> checkDump();

    void showFilterListResult();

    void showFilterListResult(String title);

    void showList(List<Flight> flightList,Boolean showCountElement);

    List<Flight> getFlightList();
}
