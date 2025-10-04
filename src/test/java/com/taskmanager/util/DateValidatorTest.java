package com.taskmanager.util;

import com.taskmanager.exception.InvalidDateException;
import org.testng.Assert;
import org.testng.annotations.*;

public class DateValidatorTest {

    @DataProvider(name = "dateStrings")
    public Object[][] dateStrings() {
        return new Object[][] {
                {"2025-10-10", true},
                {"2025-02-29", false},
                {"2024-02-29", true},
                {"2025-13-01", false},
                {"invalid-date", false}
        };
    }

    @Test(dataProvider = "dateStrings")
    public void testParseDate(String date, boolean isValid) {
        try {
            DateValidator.parseDate(date);
            Assert.assertTrue(isValid);
        } catch (InvalidDateException ex) {
            Assert.assertFalse(isValid);
        }
    }
}
