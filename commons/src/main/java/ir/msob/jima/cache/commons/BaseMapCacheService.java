package ir.msob.jima.cache.commons;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface BaseMapCacheService {

    <T> Optional<T> get(String group, String key);

    <T> T put(String group, String key, T value);

    <T> T put(String group, String key, T value, long ttl, TimeUnit ttlUnit);

    <T> T put(String group, String key, T value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit);

    <T> Optional<T> getAndRemove(String group, String key);

    boolean remove(String group);

}
