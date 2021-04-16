package com.gridnine.testing.filters;

import com.gridnine.testing.flight.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Filter implements FiltersImplement {


    private final List<Flight> flightList;
    private List<Flight> result;

    public Filter(List<Flight> flightList) {
        this.flightList = flightList;
        result = new ArrayList<>();
    }

    protected void setResult(Flight flight) {
        result.add(flight);
    }

    public List<Flight> getResult() {
        return result;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }



    public void showFilterListResult(String title)
    {
        System.out.println(title);
        showFilterListResult();
    }

    public void showFilterListResult()
    {
        showList(getResult());
    }


    public void showList(List<Flight> flightList) {

        if (flightList.isEmpty())
        {
            System.out.println("Возможно вы не запустили фильтрацию, либо фильтрация ничего не нашла!");
            return;
        }

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