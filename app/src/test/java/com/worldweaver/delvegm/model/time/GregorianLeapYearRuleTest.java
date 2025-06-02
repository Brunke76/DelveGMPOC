package com.worldweaver.delvegm.model.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GregorianLeapYearRuleTest {

    LeapYearRule leapYearRule = new GregorianLeapYearRule();
    @Test
    void useLeapYears() {
        assertTrue(leapYearRule.useLeapYears());
    }

    @Test
    void getLeapMonth() {
        assertEquals(2, leapYearRule.getLeapMonth());
    }

    @Test
    void getLeapMonthIndex() {
        assertEquals(1, leapYearRule.getLeapMonthIndex());
    }

    @Test
    void getAdjustedSecondsInYear() {
        int daysInYear = 365;
        int secondsInDay = 86400;
        assertEquals(31556925, leapYearRule.getAdjustedSecondsInYear(daysInYear, secondsInDay));
    }

    @Test
    void testGetDaysBetweenYears() {
        int daysInRegularYear = 365;
        int daysBetween = leapYearRule.getNumberOfDaysBetweenYears(2020, 2021, daysInRegularYear);
        assertEquals(366, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(2021, 2022, daysInRegularYear);
        assertEquals(365, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(2020, 2024, daysInRegularYear);
        assertEquals(1461, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(1920, 2024, daysInRegularYear);
        assertEquals(37985, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(1020, 2024, daysInRegularYear);
        assertEquals(366703, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(1921, 2021, daysInRegularYear);
        assertEquals(36525, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(1921, 2022, daysInRegularYear);
        assertEquals(36889, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(1, 2022, daysInRegularYear);
        assertEquals(738154, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(50, 2022, daysInRegularYear);
        assertEquals(720257, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(50, 151, daysInRegularYear);
        assertEquals(36889, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(50, 152, daysInRegularYear);
        assertEquals(37254, daysBetween);
        daysBetween = leapYearRule.getNumberOfDaysBetweenYears(51, 2022, daysInRegularYear);
        assertEquals(719892, daysBetween);
    }

    @Test
    void isLeapMonth() {
        // Check months of a leap year
        for(int i=1; i<=12; i++) {
            for (int j = 1; j < 100; j++) {
                int currentYear = 2000 + j;
                // if it is February and a leap year it should be true
                if (i == 2 && j % 4 == 0) {
                    assertTrue(leapYearRule.isLeapMonth(currentYear, i));
                } else {
                    assertFalse(leapYearRule.isLeapMonth(currentYear, i));
                }
            }
        }
    }

    @Test
    void isLeapYear() {
        // Check that individual years between 1-99 follow correct pattern (only true when divisible by 4)
        for(int i=1; i<100; i++) {
            int checkYear = 2000 + i;
            if (checkYear % 4 == 0) {
                assertTrue(leapYearRule.isLeapYear(checkYear));
            }
            else {
                assertFalse(leapYearRule.isLeapYear(checkYear));
            }
        }
        // Check that years divisible by 100 follow the correct pattern (only true when divisible by 400)
        for(int i=100; i<3000; i+=100) {
            if (i % 400 == 0) {
                assertTrue(leapYearRule.isLeapYear(i));
            }
            else {
                assertFalse(leapYearRule.isLeapYear(i));
            }
        }
    }
}