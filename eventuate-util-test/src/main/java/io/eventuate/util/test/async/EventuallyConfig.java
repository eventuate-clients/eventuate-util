package io.eventuate.util.test.async;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class EventuallyConfig {
    public final String message;
    public final int iterations;
    public final int timeout;
    public final TimeUnit timeUnit;

    public EventuallyConfig(String message, int iterations, int timeout, TimeUnit timeUnit) {
        this.message = message;
        this.iterations = iterations;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public static class Builder {
        private String message = null;

        private int iterations = Optional
                .ofNullable(System.getenv("EVENTUATE_TEST_UTIL_DEFAULT_ITERATIONS"))
                .map(Integer::parseInt)
                .orElse(20);

        private int timeout = Optional
                .ofNullable(System.getenv("EVENTUATE_TEST_UTIL_DEFAULT_INTERVAL_IN_MILLIS"))
                .map(Integer::parseInt)
                .orElse(500);

        private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withIterations(int iterations) {
            this.iterations = iterations;
            return this;
        }

        public Builder withInterval(int timeout, TimeUnit timeUnit) {
            this.timeout = timeout;
            this.timeUnit = timeUnit;
            return this;
        }

        public EventuallyConfig build() {
            return new EventuallyConfig(message, iterations, timeout, timeUnit);
        }

        public void eventually(Runnable body) {
            Eventually.eventually(build(), body);
        }

        public <T> T eventuallyReturningValue(Supplier<T> body) {
            return Eventually.eventuallyReturning(build(), body);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public static EventuallyConfig defaults = builder().build();

}
