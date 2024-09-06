package com.betterbackendllc.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.betterbackendllc.backend.utils.PrimeUtils;
import com.betterbackendllc.backend.utils.RandomNumberGenerator;

public class GameService {

    private Map<String, List<SpinResult>> userHistory = new ConcurrentHashMap<>();

    private RandomNumberGenerator randomNumberGenerator;

    private Map<Integer, Boolean> primeMap;

    public GameService(String serverSeed, int maxRandomNumber) {
        randomNumberGenerator = new RandomNumberGenerator(serverSeed, maxRandomNumber);
        primeMap = PrimeUtils.getPrimeNumbersMap(maxRandomNumber);
    }

    public SpinResult spin(String userId, String clientSeed) throws Exception {
        int nonce = getUserNonce(userId);
        int randomNumber = randomNumberGenerator.generateRandomNumber(clientSeed, nonce);

        boolean isPrime = primeMap.containsKey(randomNumber);

        SpinResult result = SpinResult.builder()
                .userId(userId)
                .generatedNumber(randomNumber)
                .prime(isPrime)
                .timestamp(System.currentTimeMillis())
                .build();

        saveSpinResult(userId, result);

        return result;
    }

    private void saveSpinResult(String userId, SpinResult result) {
        userHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(result);
    }

    private int getUserNonce(String userId) {
        return userHistory.getOrDefault(userId, Collections.emptyList()).size();
    }

    public List<SpinResult> getUserHistory(String userId) {
        return Collections.unmodifiableList(userHistory.getOrDefault(userId, Collections.emptyList()));
    }
}
