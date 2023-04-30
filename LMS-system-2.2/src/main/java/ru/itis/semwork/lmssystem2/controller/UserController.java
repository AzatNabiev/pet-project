package ru.itis.semwork.lmssystem2.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semwork.lmssystem2.dto.UserDto;
import ru.itis.semwork.lmssystem2.form.UserForm;
import ru.itis.semwork.lmssystem2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting the user by id")
    public ResponseEntity<UserDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editing the user by id")
    public ResponseEntity<UserDto> edit(@PathVariable(name = "id") Long id,
                                        @RequestBody UserForm userForm) {
        return ResponseEntity.ok(userService.edit(id, userForm));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting the user by id")
    public ResponseEntity<UserDto> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @PostMapping("/{user-id}/lesson/{lesson-id}")
    @Operation(summary = "Possibility to be inserted into lesson")
    public ResponseEntity<UserDto> addUserToLesson(@PathVariable(name = "user-id") Long userId,
                                                   @PathVariable(name = "lesson-id") Long lessonId){

        return ResponseEntity.ok(userService.addUserLesson(userId, lessonId));
    }

    @DeleteMapping("/{user-id}/lesson/{lesson-id}")
    @Operation(summary = "Possibility to be deleted from lesson")
    public ResponseEntity<UserDto> deleteUserFromLesson(@PathVariable(name = "user-id") Long userId,
                                                        @PathVariable(name = "lesson-id") Long lessonId){

        return ResponseEntity.ok(userService.removeFromLesson(userId, lessonId));
    }

}
