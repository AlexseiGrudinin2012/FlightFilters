/*
import com.gridnine.testing.filters.FiltersImplement;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class testFlightBuilder extends TestCase {


    private Filters filter;


    @Override
    public void setUp() {

        List<Flight> flightList = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.of(2020, 12, 31, 12, 12, 59);
        LocalDateTime now = LocalDateTime.now();

        //полет по времени ранее чем текущее время - все кроме idFlight ==1


        segments.add(new Segment(now.plusSeconds(1), now.plusHours(1).plusMinutes(30)));
        flightList.add(new Flight(new ArrayList<>(segments), 1));
        segments.clear();

        //самолет не вылетел, но уже приземлился
        segments.add(new Segment(dateTime, dateTime.plusHours(2)));
        segments.add(new Segment(dateTime.minusHours(3), dateTime.plusHours(4)));
        segments.add(new Segment(dateTime, dateTime.plusHours(7)));
        flightList.add(new Flight(new ArrayList<>(segments), 2));
        segments.clear();


        segments.add(new Segment(dateTime.minusDays(2), dateTime.plusHours(2)));
        segments.add(new Segment(dateTime.plusHours(3), dateTime.plusHours(4)));
        flightList.add(new Flight(new ArrayList<>(segments), 3));
        segments.clear();

        //стоянка между всеми перелетами более чем 2 часа

        segments.add(new Segment(dateTime, dateTime.plusHours(1)));
        segments.add(new Segment(dateTime.plusHours(2).minusSeconds(59), dateTime.plusHours(3)));
        segments.add(new Segment(dateTime.plusHours(4).minusMinutes(29), dateTime.plusHours(5)));
        segments.add(new Segment(dateTime.plusHours(6).minusMinutes(30), dateTime.plusHours(7)));
        flightList.add(new Flight(new ArrayList<>(segments), 5));
        filter = new Filters(flightList);
    }

    private List<String> getAllDatesForActualTests(List<Flight> actualFlightList) {
        List<String> actual = new ArrayList<>();
        actualFlightList
                .forEach(f ->
                        {
                            f.getSegments()
                                    .forEach(
                                            s ->
                                            {
                                                actual.add(s.getDepartureDate().toString());
                                                actual.add(s.getArrivalDate().toString());
                                            }
                                    );
                        }
                );

        return actual;
    }

    @Test
    public void testGetDepartureTimeBeforeNow() //фильтр по времени ранее чем сейчас
    {
        var dumpFilter = filter.getDepartureTimeBeforeNow();
        var actual = getAllDatesForActualTests(dumpFilter);
        List<String> expected = new ArrayList<>();

        FiltersImplement.showAllFlight(dumpFilter, "GetDepartureTimeBeforeNow TEST");

        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T14:12:59");
        expected.add("2020-12-31T09:12:59");
        expected.add("2020-12-31T16:12:59");
        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T19:12:59");
        expected.add("2020-12-29T12:12:59");
        expected.add("2020-12-31T14:12:59");
        expected.add("2020-12-31T15:12:59");
        expected.add("2020-12-31T16:12:59");
        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T13:12:59");
        expected.add("2020-12-31T14:12");
        expected.add("2020-12-31T15:12:59");
        expected.add("2020-12-31T15:43:59");
        expected.add("2020-12-31T17:12:59");
        expected.add("2020-12-31T17:42:59");
        expected.add("2020-12-31T19:12:59");

        assertEquals(expected, actual);

    }

    @Test
    public void testGetArrivalDateLessDepartureDate() // самолет не вылетел, но уже приземлился
    {
        var dumpFilter = filter.getArrivalDateLessDepartureDate();
        var actual = getAllDatesForActualTests(dumpFilter);
        List<String> expected = new ArrayList<>();

        FiltersImplement.showAllFlight(dumpFilter, "GetArrivalDateLessDepartureDate TEST");

        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T14:12:59");
        expected.add("2020-12-31T09:12:59");
        expected.add("2020-12-31T16:12:59");
        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T19:12:59");

        assertEquals(expected, actual);

    }

    @Test
    public void testGetFlightWithTransferMoreThanTwoHours() //простаивание более 2х часов
    {

        var dumpFilter = filter.getFlightWithTransferMoreThanTwoHours();
        var actual = getAllDatesForActualTests(dumpFilter);
        List<String> expected = new ArrayList<>();

        FiltersImplement.showAllFlight(dumpFilter, "GetFlightWithTransferMoreThanTwoHours TEST");


        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T13:12:59");
        expected.add("2020-12-31T14:12");
        expected.add("2020-12-31T15:12:59");
        expected.add("2020-12-31T15:43:59");
        expected.add("2020-12-31T17:12:59");
        expected.add("2020-12-31T17:42:59");
        expected.add("2020-12-31T19:12:59");
        assertEquals(expected, actual);

    }

}

*/