package com.betterbackendllc.backend;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpinResult {
    private String userId;
    private int generatedNumber;
    private boolean prime;
    private long timestamp;
}
