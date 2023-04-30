package ru.itis.semwork.lmssystem2.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semwork.lmssystem2.dto.TokenDto;
import ru.itis.semwork.lmssystem2.form.UserForm;
import ru.itis.semwork.lmssystem2.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "User auth")
    public ResponseEntity<TokenDto> login(@RequestBody UserForm userForm) {
        return ResponseEntity.ok(userService.login(userForm));
    }
}
