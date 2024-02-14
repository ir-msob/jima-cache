package ir.msob.jima.cache.ral.redisson.beans;

import ir.msob.jima.cache.commons.BaseMapCacheService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MapCacheService implements BaseMapCacheService {
    private final RedissonClient redissonClient;

    @Override
    public <T> Optional<T> get(String group, String key) {
        RMapCache<String, T> mapCache = redissonClient.getMapCache(group);
        T value = mapCache.get(key);
        return value == null ? Optional.empty() : Optional.of(value);
    }

    @Override
    public <T> T put(String group, String key, T value) {
        RMapCache<String, T> mapCache = redissonClient.getMapCache(group);
        return mapCache.put(key, value);
    }

    @Override
    public <T> T put(String group, String key, T value, long ttl, TimeUnit ttlUnit) {
        RMapCache<String, T> mapCache = redissonClient.getMapCache(group);
        return mapCache.put(key, value, ttl, ttlUnit);
    }

    @Override
    public <T> T put(String group, String key, T value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        RMapCache<String, T> mapCache = redissonClient.getMapCache(group);
        return mapCache.put(key, value, ttl, ttlUnit, maxIdleTime, maxIdleUnit);
    }

    @Override
    public <T> Optional<T> getAndRemove(String group, String key) {
        RMapCache<String, T> mapCache = redissonClient.getMapCache(group);
        T value = mapCache.remove(key);
        return value == null ? Optional.empty() : Optional.of(value);
    }

    @Override
    public boolean remove(String group) {
        return redissonClient.getMapCache(group).delete();
    }
}
