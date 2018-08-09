package com.ceszke.security.mllogin.function;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonFunctionsTest {

    @Test
    public void mean() {
        assertEquals(2,CommonFunctions.mean(new int[]{1, 2, 3}));
        assertEquals(20,CommonFunctions.mean(new int[]{5, 10, 15, 20, 50}));

    }

    @Test
    public void variance() {
        int[] data = new int[]{4,-3,2};
        assertEquals(26/3,CommonFunctions.variance(data, CommonFunctions.mean(data)));
    }
}