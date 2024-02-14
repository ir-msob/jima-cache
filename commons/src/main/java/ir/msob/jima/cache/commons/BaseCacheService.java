package ir.msob.jima.cache.commons;

import java.time.Duration;
import java.util.Optional;

public interface BaseCacheService {

    <T> Optional<T> get(String key, Class<T> clazz);

    <T> Optional<T> getAndRemove(String key, Class<T> clazz);

    <T> T put(String key, T value);

    <T> T put(String key, T value, Duration timeout);

    boolean remove(String key);

}
