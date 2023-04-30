package ru.itis.semwork.lmssystem2.service;

public interface JwtBlacklistService {
    void add(String token);

    boolean exists(String token);
}
