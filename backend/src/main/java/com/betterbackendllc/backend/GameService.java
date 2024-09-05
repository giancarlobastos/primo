package com.betterbackendllc.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.betterbackendllc.backend.utils.PrimeUtils;
import com.betterbackendllc.backend.utils.RandomNumberGenerator;

public class GameService {

    private static final int MAX_RANDOM_NUMBER = 20;

    private static final String SERVER_SEED = "<<SUPER-SECRET-SERVER-SEED>>";

    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new RandomNumberGenerator(SERVER_SEED,
            MAX_RANDOM_NUMBER);

    private static final Map<Integer, Boolean> PRIME_MAP = PrimeUtils.getPrimeNumbersMap(MAX_RANDOM_NUMBER);

    private static Map<String, List<SpinResult>> userHistory = new ConcurrentHashMap<>();

    public static SpinResult spin(String userId, String clientSeed) throws Exception {
        int nonce = getUserNonce(userId);
        int randomNumber = RANDOM_NUMBER_GENERATOR.generateRandomNumber(clientSeed, nonce);

        boolean isPrime = isPrime(randomNumber);

        SpinResult result = SpinResult.builder()
                .userId(userId)
                .generatedNumber(randomNumber)
                .prime(isPrime)
                .timestamp(System.currentTimeMillis())
                .build();

        saveSpinResult(userId, result);

        return result;
    }

    private static void saveSpinResult(String userId, SpinResult result) {
        userHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(result);
    }

    private static int getUserNonce(String userId) {
        return userHistory.getOrDefault(userId, Collections.emptyList()).size();
    }

    public static List<SpinResult> getUserHistory(String userId) {
        return Collections.unmodifiableList(userHistory.getOrDefault(userId, Collections.emptyList()));
    }

    public static boolean isPrime(int n) {
        return PRIME_MAP.containsKey(n);
    }
}
