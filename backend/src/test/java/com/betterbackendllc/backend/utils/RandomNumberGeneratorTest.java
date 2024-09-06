package com.betterbackendllc.backend.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.crypto.Mac;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import lombok.SneakyThrows;

public class RandomNumberGeneratorTest {

    @Test
    @SneakyThrows
    public void shouldReturnNumberInTheRangeOf1To20() {
        try (MockedStatic<Mac> mockedSha256 = Mockito.mockStatic(Mac.class)) {
            Mac mac = mock(Mac.class);
            when(Mac.getInstance("HmacSHA256")).thenReturn(mac);
            when(mac.doFinal(any(byte[].class))).thenReturn(new byte[] { 0x7F }); // 127

            String clientSeed = "testClientSeed";
            int nonce = 1;

            RandomNumberGenerator generator = new RandomNumberGenerator("<<SECRET>>", 20);
            int generatedNumber = generator.generateRandomNumber(clientSeed, nonce);

            verify(mac).doFinal(any(byte[].class));
            assertThat(generatedNumber).isEqualTo(8); // f(n) = (n % 20) + 1
        }
    }
}
