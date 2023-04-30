package ru.itis.semwork.lmssystem2.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.semwork.lmssystem2.dto.UserDto;
import ru.itis.semwork.lmssystem2.form.UserForm;
import ru.itis.semwork.lmssystem2.model.User;
import ru.itis.semwork.lmssystem2.model.enums.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final LessonMapper lessonMapper;

    public User mapToUser(UserForm userForm){

        return User.builder()
                .email(userForm.getEmail())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .role(UserRole.valueOf(userForm.getRole()))
                .profilePhoto(userForm.getProfilePhoto())
                .tgAlias(userForm.getTgAlias())
                .since(userForm.getSince()).build();
    }

    public List<UserDto> mapUserDto(List<User> users) {
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().toString())
                .redisId(user.getRedisId())
                .state(user.getState().toString())
                .profilePhoto(user.getProfilePhoto())
                .tgAlias(user.getTgAlias())
                .since(user.getSince())
                .createdDate(user.getCreatedDate())
                .lessons(user.getLessons() != null ? lessonMapper.mapToLessonDto(user.getLessons()) : new ArrayList<>())
                .updateDate(user.getUpdateDate()).build();
    }
}
