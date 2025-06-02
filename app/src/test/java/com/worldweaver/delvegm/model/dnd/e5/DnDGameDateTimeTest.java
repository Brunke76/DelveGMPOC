package com.worldweaver.delvegm.model.dnd.e5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.worldweaver.delvegm.model.time.GameCalendar;
import com.worldweaver.delvegm.model.time.GameMonth;
import com.worldweaver.delvegm.model.time.InvalidDateTimeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class DnDGameDateTimeTest {

    private static final GameMonth MONTH_1 = GameMonth.builder().numberOfDays(31).build();
    private static final GameMonth MONTH_2 = GameMonth.builder().numberOfDays(28).build();
    private static final GameMonth MONTH_3 = GameMonth.builder().numberOfDays(31).build();
    private static final GameMonth MONTH_4 = GameMonth.builder().numberOfDays(30).build();
    private static final List<GameMonth> MONTH_LIST = List.of(MONTH_1, MONTH_2, MONTH_3, MONTH_4);
    private static final int TARGET_YEAR = 2025;
    private static final int TARGET_MONTH = 4;
    private static final int TARGET_DAY = 24;
    private static final int TARGET_HOUR = 13;
    private static final int TARGET_MINUTE = 45;
    private static final int TARGET_SECOND = 36;

    @Mock
    private GameCalendar calendar;
    private DnDGameDateTime.DnDGameDateTimeBuilder builder;

    @BeforeEach
    void setup() {
        builder = new DnDGameDateTime.DnDGameDateTimeBuilder(calendar, TARGET_YEAR);
    }

    @Test
    void testThatErrorIsThrownForDayZero() {
        try {
            builder.day(0);
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("day can be a positive or negative integer, but cannot be 0", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testThatErrorIsThrownForMonthZero() {
        try {
            builder.month(0);
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("month can be a positive or negative integer, but cannot be 0", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testThatErrorIsThrownForYearZero() {
        try {
            builder = new DnDGameDateTime.DnDGameDateTimeBuilder(calendar, 0);
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("year can be a positive or negative integer, but cannot be 0", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testThatErrorIsThrownForYearZeroForBuilderMethod() {
        try {
            builder = DnDGameDateTime.builder(calendar, 0);
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("year can be a positive or negative integer, but cannot be 0", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testBuilderMethod() {
        builder = DnDGameDateTime.builder(calendar, TARGET_YEAR);
        DnDGameDateTime.DnDGameDateTimeBuilder expected =
                new DnDGameDateTime.DnDGameDateTimeBuilder(calendar, TARGET_YEAR);

        assertEquals(expected.toString(), builder.toString());
    }

    @Test
    void testBuildsFullObject() {
        int hoursInDay = 24;
        int minutesInHour = 60;
        int secondsInMinute = 60;
        int secondsInDay = secondsInMinute * minutesInHour * hoursInDay;
        int targetSecondOfDay =
                (((TARGET_HOUR * minutesInHour) + TARGET_MINUTE) * secondsInMinute) + TARGET_SECOND;
        when(calendar.getMonths()).thenReturn(MONTH_LIST);
        when(calendar.getDaysInYear(anyInt())).thenReturn(365);
        when(calendar.getDaysInMonth(TARGET_YEAR, 1)).thenReturn(31);
        when(calendar.getDaysInMonth(TARGET_YEAR, 2)).thenReturn(28);
        when(calendar.getDaysInMonth(TARGET_YEAR, 3)).thenReturn(30);
        when(calendar.getSecondsInDay()).thenReturn(secondsInDay);
        when(calendar.getHoursInDay()).thenReturn(hoursInDay);
        when(calendar.getMinutesInHour()).thenReturn(minutesInHour);
        when(calendar.getSecondsInMinute()).thenReturn(secondsInMinute);
        DnDGameDateTime result = DnDGameDateTime.builder(calendar, TARGET_YEAR)
                                                .month(TARGET_MONTH)
                                                .day(TARGET_DAY)
                                                .hour(TARGET_HOUR)
                                                .minute(TARGET_MINUTE)
                                                .second(TARGET_SECOND)
                                                .build();

        assertEquals(TARGET_YEAR, result.getYear());
//        assertEquals(TARGET_MONTH, result.getMonth());
//        assertEquals(TARGET_DAY, result.getDay());
        assertEquals(TARGET_HOUR, result.getHour());
        assertEquals(TARGET_MINUTE, result.getMinute());
        assertEquals(TARGET_SECOND, result.getSecond());
        assertEquals(TARGET_SECOND, targetSecondOfDay);

    }

}