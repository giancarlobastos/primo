package com.betterbackendllc.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import lombok.SneakyThrows;

public class GameServiceTest {

    @Test
    @SneakyThrows
    public void shouldReturnConsistentValueWhenUsingSameSeeds() {
        GameService gameService = new GameService("testSeed", 20);
        SpinResult result = gameService.spin("user1", "clientSeed1");

        // as results must be reproduced when used the same server_seed, user_seed,
        // nonce, the results must be the same always we have a new GameService
        assertThat(result.getGeneratedNumber()).isEqualTo(16);
        assertThat(result.isPrime()).isFalse();

        result = gameService.spin("user1", "clientSeed1");
        assertThat(result.getGeneratedNumber()).isEqualTo(13);
        assertThat(result.isPrime()).isTrue();

        result = gameService.spin("user1", "clientSeed1");
        assertThat(result.getGeneratedNumber()).isEqualTo(9);
        assertThat(result.isPrime()).isFalse();

        result = gameService.spin("user1", "clientSeed1");
        assertThat(result.getGeneratedNumber()).isEqualTo(13);
        assertThat(result.isPrime()).isTrue();

        result = gameService.spin("user1", "clientSeed1");
        assertThat(result.getGeneratedNumber()).isEqualTo(12);
        assertThat(result.isPrime()).isFalse();

        List<SpinResult> results = gameService.getUserHistory("user1");
        assertThat(results).hasSize(5);
        assertThat(results.get(0).isPrime()).isFalse();
        assertThat(results.get(1).isPrime()).isTrue();
        assertThat(results.get(2).isPrime()).isFalse();
        assertThat(results.get(3).isPrime()).isTrue();
        assertThat(results.get(4).isPrime()).isFalse();
    }
}
