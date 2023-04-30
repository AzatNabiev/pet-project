package ru.itis.semwork.lmssystem2.repository;

public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}
