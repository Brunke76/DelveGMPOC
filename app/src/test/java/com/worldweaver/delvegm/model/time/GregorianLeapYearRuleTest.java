package com.worldweaver.delvegm.model.time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GregorianLeapYearRuleTest {

    LeapYearRule leapYearRule = new GregorianLeapYearRule();
    @Test
    void useLeapYears() {
        Assertions.assertTrue(leapYearRule.useLeapYears());
    }

    @Test
    void getLeapMonth() {
        Assertions.assertEquals(2, leapYearRule.getLeapMonth());
    }

    @Test
    void getLeapMonthIndex() {
        Assertions.assertEquals(1, leapYearRule.getLeapMonthIndex());
    }

    @Test
    void getAdjustedSecondsInYear() {
        int daysInYear = 365;
        int secondsInDay = 86400;
        Assertions.assertEquals(31556925, leapYearRule.getAdjustedSecondsInYear(daysInYear, secondsInDay));
    }

    @Test
    void isLeapMonth() {
        // Check months of a leap year
        for(int i=1; i<=12; i++) {
            for (int j = 1; j < 100; j++) {
                int currentYear = 2000 + j;
                // if it is February and a leap year it should be true
                if (i == 2 && j % 4 == 0) {
                    Assertions.assertTrue(leapYearRule.isLeapMonth(currentYear, i));
                } else {
                    Assertions.assertFalse(leapYearRule.isLeapMonth(currentYear, i));
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
                Assertions.assertTrue(leapYearRule.isLeapYear(checkYear));
            }
            else {
                Assertions.assertFalse(leapYearRule.isLeapYear(checkYear));
            }
        }
        // Check that years divisible by 100 follow the correct pattern (only true when divisible by 400)
        for(int i=100; i<3000; i+=100) {
            if (i % 400 == 0) {
                Assertions.assertTrue(leapYearRule.isLeapYear(i));
            }
            else {
                Assertions.assertFalse(leapYearRule.isLeapYear(i));
            }
        }
    }
}