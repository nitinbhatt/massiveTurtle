package com.nitin.massiveTurtle.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void testNominal() {
        String date = DateUtils.convertToDate(1673452695000L);
        assertEquals("2023-01-11", date);
    }

    @Test
    public void testDateZero() {
        String date = DateUtils.convertToDate(0L);
        assertEquals("1969-12-31", date);
    }
}