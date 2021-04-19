package com.gridnine.testing.filters.parents;

import com.gridnine.testing.flight.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Filter implements FiltersImplement {


    private final List<Flight> flightList;
    private final List<Flight> result;
    private final String ERR_MSG = "Возможно вы не запустили фильтрацию, либо фильтрация ничего не нашла!";
    private final Boolean SHOW_COUNT_ELEMENTS = true;

    public Filter(List<Flight> flightList) {
        this.flightList = flightList;
        result = new ArrayList<>();
    }


    protected void setResult(Flight flight) {
        result.add(flight);
    }

    public List<Flight> getResult() {
        return result.isEmpty() ? new ArrayList<>() : result;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }


    public void showFilterListResult(String title) {
        System.out.println(title);
        showFilterListResult();
    }

    public void showFilterListResult() {
        showList(getResult(), SHOW_COUNT_ELEMENTS);
    }


    public void showList(List<Flight> flightList, Boolean showCountElements) {

        if (flightList.isEmpty()) {
            System.out.println(ERR_MSG);
            return;
        }


        String showElement = showCountElements ? "Найдено элементов - " + flightList.size() : "";
        System.out.println(showElement);
        getResult().forEach(f ->

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

    @Override
    public abstract List<Flight> checkDump();

}