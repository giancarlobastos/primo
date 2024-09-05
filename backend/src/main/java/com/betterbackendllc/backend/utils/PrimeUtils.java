package com.betterbackendllc.backend.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PrimeUtils {

    /**
     * It uses Sieve of Eratosthenes algorithm to generate a map containing the
     * prime numbers in the range of [2,n].
     */
    public static Map<Integer, Boolean> getPrimeNumbersMap(int n) {
        Map<Integer, Boolean> map = new HashMap<>();

        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                map.put(i, true);
            }
        }

        return Collections.unmodifiableMap(map);
    }
}
