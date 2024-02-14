package ir.msob.jima.cache.ral.redis.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.msob.jima.cache.commons.BaseCacheService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheService implements BaseCacheService {

    private final ReactiveStringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public <T> Optional<T> get(String key, Class<T> clazz) {
        String value = redisTemplate.opsForValue()
                .get(key)
                .toFuture()
                .get();
        return Strings.isBlank(value) ? Optional.empty() : Optional.of(objectMapper.reader().readValue(value, clazz));
    }

    @SneakyThrows
    @Override
    public <T> Optional<T> getAndRemove(String key, Class<T> clazz) {
        String value = redisTemplate.opsForValue()
                .getAndDelete(key)
                .toFuture()
                .get();
        return Strings.isBlank(value) ? Optional.empty() : Optional.of(objectMapper.reader().readValue(value, clazz));
    }

    @SneakyThrows
    @Override
    public <T> T put(String key, T value) {
        redisTemplate.opsForValue()
                .set(key, objectMapper.writeValueAsString(value))
                .toFuture()
                .get();
        return value;
    }

    @SneakyThrows
    @Override
    public <T> T put(String key, T value, Duration duration) {
        redisTemplate.opsForValue()
                .set(key, objectMapper.writeValueAsString(value), duration)
                .toFuture()
                .get();
        return value;
    }

    @Override
    public boolean remove(String key) {
        return false;
    }
}
