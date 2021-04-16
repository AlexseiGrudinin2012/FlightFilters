import com.gridnine.testing.filters.ArrivalDateLessDepartureDateFilter;
import com.gridnine.testing.filters.DepartureTimeBeforeNow;
import com.gridnine.testing.filters.FiltersImplement;
import com.gridnine.testing.filters.FlightForParkingWithTimeAFilter;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class testFiltersClass extends TestCase {


    List<Flight> flightList = new ArrayList<>();

    @Override
    public void setUp() {


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
    public void testArrivalDateLessDepartyreDateFilter() {

        var results =
                new ArrivalDateLessDepartureDateFilter(flightList);
        var actual = getAllDatesForActualTests(results.checkDump());
        results.showFilterListResult("тест 1");
        List<String> expected = new ArrayList<>();

        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T14:12:59");
        expected.add("2020-12-31T09:12:59");
        expected.add("2020-12-31T16:12:59");
        expected.add("2020-12-31T12:12:59");
        expected.add("2020-12-31T19:12:59");

        assertEquals(expected, actual);
    }

    public void testDepartureTimeBeforeNow() {

        var results =
                new DepartureTimeBeforeNow(flightList);

        var actual = getAllDatesForActualTests(results.checkDump());
        results.showFilterListResult("тест 2");
        List<String> expected = new ArrayList<>();
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

    public void testFlightForParkingWithTimeAFilterTest1() {

        var results =
                new FlightForParkingWithTimeAFilter(flightList);
        var actual = getAllDatesForActualTests(results.checkDump());
        results.showFilterListResult("тест 3");
        List<String> expected = new ArrayList<>();
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

    public void testFlightForParkingWithTimeAFilterTest2() {

        var results =
                new FlightForParkingWithTimeAFilter(flightList);

        results.setHour(3);
        var actual = results.checkDump();

        results.showFilterListResult("тест 4");
        assertNull(actual);
    }


}

