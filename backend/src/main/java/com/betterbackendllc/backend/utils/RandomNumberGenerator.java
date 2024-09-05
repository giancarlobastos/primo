package com.betterbackendllc.backend.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Generates random numbers cryptographically secure using server seed, client
 * seed, and nonce.
 */
public class RandomNumberGenerator {

    private static final String HMAC_SHA256 = "HmacSHA256";

    @Getter
    private String serverSeed;

    @Getter
    private int randomNumberMax;

    private Mac sha256;

    @SneakyThrows
    public RandomNumberGenerator(String serverSeed, int randomNumberMax) {
        this.serverSeed = serverSeed;
        this.randomNumberMax = randomNumberMax;
        this.sha256 = Mac.getInstance(HMAC_SHA256);
        sha256.init(new SecretKeySpec(serverSeed.getBytes(), HMAC_SHA256));
    }

    @SneakyThrows
    public int generateRandomNumber(String clientSeed, int nonce) {
        String data = clientSeed + "-" + nonce;
        byte[] hash = sha256.doFinal(data.getBytes());
        int number = (Math.abs(hash[0]) % randomNumberMax) + 1;
        return number;
    }
}
