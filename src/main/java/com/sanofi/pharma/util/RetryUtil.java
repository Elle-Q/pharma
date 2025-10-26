package com.sanofi.pharma.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELLE Q
 * @since 2025-10-26
 */
@Component
@Slf4j
public class RetryUtil {

    private static final Integer RETRY_ATTEMPT = 5;

    public void retry() {
        int count = 0;
        while (count < RETRY_ATTEMPT) {

        }
    }
}
