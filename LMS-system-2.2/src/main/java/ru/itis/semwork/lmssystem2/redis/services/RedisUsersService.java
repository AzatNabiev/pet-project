package ru.itis.semwork.lmssystem2.redis.services;


import ru.itis.semwork.lmssystem2.model.User;

public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}
