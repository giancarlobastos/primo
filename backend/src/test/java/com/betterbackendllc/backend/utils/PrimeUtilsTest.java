package com.betterbackendllc.backend.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;

public class PrimeUtilsTest {

    @Test
    public void shouldGetPrimeNumbersMapWithValidInput() {
        Map<Integer, Boolean> primes = PrimeUtils.getPrimeNumbersMap(10);
        assertThat(primes.keySet()).containsExactlyInAnyOrder(2, 3, 5, 7);
        assertThat(primes.values()).containsOnly(true);
    }

    @Test
    public void shouldGetPrimeNumbersMapWhenNLessThanTwo() {
        Map<Integer, Boolean> primes = PrimeUtils.getPrimeNumbersMap(1);
        assertThat(primes).isEmpty();
    }

    @Test
    public void shouldGetPrimeNumbersMapWhenLargeInput() {
        Map<Integer, Boolean> primes = PrimeUtils.getPrimeNumbersMap(30);
        Integer[] expectedPrimes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 };
        assertThat(primes.keySet()).containsExactlyInAnyOrder(expectedPrimes);
        assertThat(primes.values()).containsOnly(true);
    }
}
