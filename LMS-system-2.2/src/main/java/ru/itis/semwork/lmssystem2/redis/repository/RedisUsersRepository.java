package ru.itis.semwork.lmssystem2.redis.repository;


import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.semwork.lmssystem2.redis.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
