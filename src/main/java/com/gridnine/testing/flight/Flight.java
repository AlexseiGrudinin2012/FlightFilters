package com.gridnine.testing.flight;

import java.util.List;
import java.util.stream.Collectors;

public class Flight {

    private final List<Segment> segments;
    private final Integer idFlight;

    public Flight(final List<Segment> segments, Integer idFlight) {
        this.segments = segments;
        this.idFlight = idFlight;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }


}
