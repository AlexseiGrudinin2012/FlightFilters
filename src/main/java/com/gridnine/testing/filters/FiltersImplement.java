package com.gridnine.testing.filters;

import com.gridnine.testing.flight.Flight;

import java.util.List;


public interface FiltersImplement {
    List<Flight> checkDump();

    void showFilterListResult();
    void showFilterListResult(String title);
    void showList(List<Flight> flightList);

    List<Flight> getFlightList();
}
