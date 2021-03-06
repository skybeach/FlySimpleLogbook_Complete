package model;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogbookEntryTest {
    private LogbookEntry entry;

    @BeforeEach
    public void setUp() {
        try {
            entry = new LogbookEntry();
            entry.setEntryNumber(1);
            entry.setYear(2020);
            entry.setMonth("June");
            entry.setDay(8);
            entry.setAirplaneModel("C-172M");
            entry.setAirplaneName("GXWS");
            entry.setPic("JWei");
            entry.setFlightTime(1.4);
            entry.setDayOrNight("Day");
            entry.setDepartureAirport("CYNJ");
            entry.setArrivalAirport("CYVR");
            entry.setRemark("CheckRide");
        } catch (InvalidInputException e) {
            fail("no exception should be thrown");
        }
    }


    @Test
    public void testLogbookEntry() {
        assertEquals(1, entry.getEntryNumber());
        assertEquals(2020, entry.getYear());
        assertEquals("June", entry.getMonth());
        assertEquals(8, entry.getDay());
        assertEquals("C-172M", entry.getAirplaneModel());
        assertEquals("GXWS", entry.getAirplaneName());
        assertEquals("JWei", entry.getPic());
        assertEquals(1.4, entry.getFlightTime());
        assertEquals("Day", entry.getDayOrNight());
        assertEquals("CYNJ", entry.getDepartureAirport());
        assertEquals("CYVR", entry.getArrivalAirport());
        assertEquals("CheckRide", entry.getRemark());
    }

    @Test
    public void testThrowInvalidInputExceptionDayTooSmall() {
        try {
            entry.setDay(0);
            fail("InvalidInpuException should be thrown");
        } catch (InvalidInputException e) {
            //expected
        }
    }

    @Test
    public void testThrowInvalidInputExceptionDayTooBig() {
        try {
            entry.setDay(32);
            fail("InvalidInputException should be thrown");
        } catch (InvalidInputException e) {
            //expected
        }
    }

    @Test
    public void testThrowInvalidInputExceptionDay() {
        try {
            entry.setDay(-15);
            fail("InvalidInputException should be thrown");
        } catch (InvalidInputException e) {
            //expected
        }
    }

    @Test
    public void testEmptyInputLogInfo() {
        try {
            entry.setMonth("");
            fail("InvalidInputException should be thrown");
        } catch (InvalidInputException e) {
            try {
                entry.setAirplaneModel("");
                fail("InvalidInputException should be thrown");
            } catch (InvalidInputException a) {
                try {
                    entry.setAirplaneName("");
                    fail("InvalidInputException should be thrown");
                } catch (InvalidInputException s) {
                    try {
                        entry.setPic("");
                        fail("InvalidInputException should be thrown");
                    } catch (InvalidInputException d) {
                        //expected
                    }
                }
            }
        }
    }


    @Test
    public void testThrowInvalidAirportNameLength() {

        try {
            entry.setDayOrNight("neither");
            entry.setDepartureAirport("ABCDE");
            fail("InvalidInput should be thrown");
        } catch (InvalidInputException e) {
            try {
                entry.setArrivalAirport("ABCDE");
                fail("InvalidInput should be thrown");

            } catch (InvalidInputException a) {
                //expected
            }
        }
    }

    @Test
    public void testThrowInvalidAirportEmpty() {
        try {
            entry.setDepartureAirport("");
            fail("InvalidInput should be thrown");
        } catch (InvalidInputException e) {
            try {
                entry.setArrivalAirport("");
                fail("InvalidInput should be thrown");
            } catch (InvalidInputException a) {
                //expected
            }
        }
    }

    @Test
    public void testDayOrNightEmpty() {
        try {
            entry.setDayOrNight("");
            fail("InvalidInput should be thrown");
        } catch (InvalidInputException e) {
            try {
                entry.setDayOrNight("");
                fail("InvalidInput should be thrown");
            } catch (InvalidInputException a) {
                //expected
            }
        }
    }

    @Test
    public void testFlightTimeException() {
        try {
            entry.setFlightTime(0);
            fail("InvalidInput should be thrown");
        } catch (InvalidInputException e) {
            try {
                entry.setFlightTime(-1);
                fail("InvalidInput should be thrown");
            } catch (InvalidInputException a) {
                //expected
            }
        }
    }
}