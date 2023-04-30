package ru.itis.semwork.lmssystem2.service;

import ru.itis.semwork.lmssystem2.dto.TokenDto;
import ru.itis.semwork.lmssystem2.dto.UserDto;
import ru.itis.semwork.lmssystem2.form.UserForm;

import java.util.List;

public interface UserService {

    UserDto register(UserForm user);

    UserDto edit(Long id, UserForm user);

    UserDto delete(Long id);

    List<UserDto> getAll();

    UserDto getById(Long id);

    TokenDto login(UserForm userForm);

    UserDto addUserLesson(Long userId, Long lessonId);

    UserDto removeFromLesson(Long userId, Long lessonId);

}
