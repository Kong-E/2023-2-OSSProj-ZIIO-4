package com.ziio.backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class RandomUtil {
    private final int RANDOM_RANGE = 9;
    private final Random random = new Random();

    public Long generateRandomNumber() {
        return (long)(random.nextInt(RANDOM_RANGE) + 1);
    }
}
