package com.worldweaver.delvegm.model.time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GameCalendarTest {

    private static final int LEAP_YEAR = 2000;
    private static final int NON_LEAP_YEAR = 2001;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final List<String> WEEKDAY_NAMES = List.of("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    private static final List<GameMonth> MONTHS =
            List.of(
                    GameMonth.builder().name("January").numberOfDays(31).build(),
                    GameMonth.builder().name("February").numberOfDays(28).build(),
                    GameMonth.builder().name("March").numberOfDays(31).build(),
                    GameMonth.builder().name("April").numberOfDays(30).build(),
                    GameMonth.builder().name("May").numberOfDays(31).build(),
                    GameMonth.builder().name("June").numberOfDays(30).build(),
                    GameMonth.builder().name("July").numberOfDays(31).build(),
                    GameMonth.builder().name("August").numberOfDays(31).build(),
                    GameMonth.builder().name("September").numberOfDays(30).build(),
                    GameMonth.builder().name("October").numberOfDays(31).build(),
                    GameMonth.builder().name("November").numberOfDays(30).build(),
                    GameMonth.builder().name("December").numberOfDays(31).build()
                    );

    @Mock
    private LeapYearRule leapYearRule;
    private GameCalendar gameCalendar;

    @BeforeEach
    void setup() {
        when(leapYearRule.isLeapYear(LEAP_YEAR)).thenReturn(true);
        when(leapYearRule.isLeapYear(NON_LEAP_YEAR)).thenReturn(false);
        when(leapYearRule.getLeapMonth()).thenReturn(2);

        gameCalendar = GameCalendar.builder()
                                   .name("Test Calendar")
                                   .leapYearRule(leapYearRule)
                                   .secondsInMinute(SECONDS_IN_MINUTE)
                                   .minutesInHour(MINUTES_IN_HOUR)
                                   .hoursInDay(HOURS_IN_DAY)
                                   .weekDayNames(WEEKDAY_NAMES)
                                   .months(MONTHS)
                                   .build();
    }

    @Test
    void testGetDaysInRegularYear() {
        Assertions.assertEquals(365, gameCalendar.getDaysInRegularYear());
    }

    @Test
    void testGetAdjustedSecondsInYear() {
        when(leapYearRule.getAdjustedSecondsInYear(365, 86400)).thenReturn(31556925);
        Assertions.assertEquals(31556925, gameCalendar.getAdjustedSecondsInYear());
    }

    @Test
    void testGetSecondsInHour() {
        Assertions.assertEquals(3600, gameCalendar.getSecondsInHour());
    }

    @Test
    void testGetSecondsInDay() {
        Assertions.assertEquals(86400, gameCalendar.getSecondsInDay());
    }

    @Test
    void testGetSecondsInWeek() {
        Assertions.assertEquals(604800, gameCalendar.getSecondsInWeek());
    }

    @Test
    void testGetDaysInYear() {
        int leapYear = 2000;
        int nonLeapYear = 2001;
        when(leapYearRule.isLeapYear(leapYear)).thenReturn(true);
        when(leapYearRule.isLeapYear(nonLeapYear)).thenReturn(false);
        Assertions.assertEquals(366, gameCalendar.getDaysInYear(leapYear));
        Assertions.assertEquals(365, gameCalendar.getDaysInYear(nonLeapYear));
    }

    @Test
    void testGetDayOfYear() {
        // While the days are before the end of February,
        // the day of the year will be the same whether it is a leap year or not
        for (int m = 1; m <= 2; m++) {
            for (int d = 1; d <= MONTHS.get(m - 1).getNumberOfDays(); d++) {
                Assertions.assertEquals(
                        gameCalendar.getDayOfYear(NON_LEAP_YEAR, m, d),
                        gameCalendar.getDayOfYear(LEAP_YEAR, m, d)
                );
            }
        }
        // Starting March 1st, the leap year day of the year will be 1 more the same date in a non-leap year
        for (int m = 3; m <= MONTHS.size(); m++) {
            for (int d = 1; d <= MONTHS.get(m - 1).getNumberOfDays(); d++) {
                Assertions.assertEquals(
                        gameCalendar.getDayOfYear(NON_LEAP_YEAR, m, d) + 1,
                        gameCalendar.getDayOfYear(LEAP_YEAR, m, d)
                );
            }
        }
    }

    @Test
    void testGetDaysInMonth() {
        Assertions.assertEquals(31, gameCalendar.getDaysInMonth(LEAP_YEAR, 0));
        Assertions.assertEquals(31, gameCalendar.getDaysInMonth(NON_LEAP_YEAR, 0));
    }

}