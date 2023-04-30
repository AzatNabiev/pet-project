package ru.itis.semwork.lmssystem2.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semwork.lmssystem2.dto.LessonDto;
import ru.itis.semwork.lmssystem2.form.LessonForm;
import ru.itis.semwork.lmssystem2.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/{id}")
    @Operation(summary = "Getting the ACTIVE lesson by id")
    public ResponseEntity<LessonDto> getById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @GetMapping
    @Operation(summary = "Getting all ACTIVE lessons")
    public ResponseEntity<List<LessonDto>> getAll() {
        return ResponseEntity.ok(lessonService.getAll());
    }

    @PostMapping
    @Operation(summary = "Adding the lesson by TEACHER")
    public ResponseEntity<LessonDto> add(@RequestBody LessonForm lessonForm) {
        return ResponseEntity.ok(lessonService.add(lessonForm));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editing the lesson by Id")
    public ResponseEntity<LessonDto> edit(@PathVariable(name = "id") Long id,
                                          @RequestBody LessonForm lessonForm) {
        return ResponseEntity.ok(lessonService.edit(id, lessonForm));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting the lesson by Id")
    public ResponseEntity<LessonDto> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(lessonService.delete(id));
    }
}
