package io.eventuate.util.test.async;


import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Useful to testing asynchronous code
 * Eventually has methods that periodically executes a Runnable or Callable until it returns successfully or the iteration limit has been reached.
 */
public class Eventually {

  public static void setDefaults(EventuallyConfig eventuallyConfig) {
    EventuallyConfig.defaults = eventuallyConfig;
  }
  public static void eventually(Runnable body) {
    eventually(EventuallyConfig.defaults, body);
  }

  public static EventuallyConfig.Builder withMessage(String message) {
    return EventuallyConfig.builder().withMessage(message);
  }

  public static EventuallyConfig.Builder withIterations(int iterations) {
      return EventuallyConfig.builder().withIterations(iterations);
  }

  public static EventuallyConfig.Builder withInterval(int timeout, TimeUnit timeUnit) {
      return EventuallyConfig.builder().withInterval(timeout, timeUnit);
  }

  public static void eventually(String message, Runnable body) {
    eventually(EventuallyConfig.builder().withMessage(message).build(), body);
  }

  public static void eventually(int iterations, int timeout, TimeUnit timeUnit, Runnable body) {
    eventually(null, iterations, timeout, timeUnit, body);
  }

  public static void eventually(String message, int iterations, int timeout, TimeUnit timeUnit, Runnable body) {
    eventually(new EventuallyConfig(message, iterations, timeout, timeUnit), body);
  }

  public static void eventually(EventuallyConfig eventuallyConfig, Runnable body) {
    eventuallyReturning(eventuallyConfig, () -> {
      body.run();
      return null;
    });
  }

  public static <T> T eventuallyReturning(String message, Supplier<T> body) {
    return eventuallyReturning(message, EventuallyConfig.defaults.iterations, EventuallyConfig.defaults.timeout, TimeUnit.MILLISECONDS, body);
  }

  public static <T> T eventuallyReturning(Supplier<T> body) {
    return eventuallyReturning(null, EventuallyConfig.defaults.iterations, EventuallyConfig.defaults.timeout, TimeUnit.MILLISECONDS, body);
  }

  public static <T> T eventuallyReturning(int iterations, int timeout, TimeUnit timeUnit, Supplier<T> body) {
    return eventuallyReturning(null, iterations, timeout, timeUnit, body);
  }

  public static <T> T eventuallyReturning(String message, int iterations, int timeout, TimeUnit timeUnit, Supplier<T> body) {
    return eventuallyReturning(new EventuallyConfig(message, iterations, timeout, timeUnit), body);
  }

  public static <T> T eventuallyReturning(EventuallyConfig eventuallyConfig, Supplier<T> body) {
    Throwable t = null;
    for (int i = 0; i < eventuallyConfig.iterations; i++) {
      try {
        return body.get();
      } catch (Throwable t1) {
        t = t1;
        try {
          eventuallyConfig.timeUnit.sleep(eventuallyConfig.timeout);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
    if (eventuallyConfig.message == null)
      throw new EventuallyException("Failed after %s iterations every %s milliseconds".formatted(eventuallyConfig.iterations, eventuallyConfig.timeout), t);
    else
      throw new EventuallyException(String.format(eventuallyConfig.message + " - " + "Failed after %s iterations every %s milliseconds", eventuallyConfig.iterations, eventuallyConfig.timeout), t);
  }
}