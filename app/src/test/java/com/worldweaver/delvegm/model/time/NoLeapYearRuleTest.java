package com.worldweaver.delvegm.model.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NoLeapYearRuleTest {

    LeapYearRule leapYearRule = new NoLeapYearRule();

    @Test
    void testUseLeapYearsIsFalse() {
        Assertions.assertFalse(leapYearRule.useLeapYears());
    }

    @Test
    void testGetLeapMonth() {
        Assertions.assertEquals(Integer.MAX_VALUE, leapYearRule.getLeapMonth());
    }

    @Test
    void testGetLeapMonthIndex() {
        Assertions.assertEquals(Integer.MAX_VALUE, leapYearRule.getLeapMonthIndex());
    }

    @Test
    void testGetAdjustedSeconds() {
        int daysInRegularYear = 100;
        int secondsInADay = 2000;
        int adjustedSeconds = leapYearRule.getAdjustedSecondsInYear(daysInRegularYear, secondsInADay);
        Assertions.assertEquals(200000, adjustedSeconds);
    }

    @Test
    void testIsLeapMonth() {
        for(int i = 1; i <= 12; i++) {
            Assertions.assertFalse(leapYearRule.isLeapMonth(2000, i));
        }
    }

    @Test
    void testIsLeapYear() {
        for(int i = 0; i <= 50; i++) {
            Assertions.assertFalse(leapYearRule.isLeapYear(i));
        }
    }

}