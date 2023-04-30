package ru.itis.semwork.lmssystem2.redis.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.semwork.lmssystem2.repository.BlacklistRepository;

@Repository
@RequiredArgsConstructor
public class BlacklistRepositoryRedisTemplateImpl implements BlacklistRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String token) {
        redisTemplate.opsForValue().set(token, "");
    }

    @Override
    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.hasKey(token);
        return hasToken != null && hasToken;
    }
}
