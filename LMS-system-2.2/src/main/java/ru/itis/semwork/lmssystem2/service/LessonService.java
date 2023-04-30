package ru.itis.semwork.lmssystem2.service;

import ru.itis.semwork.lmssystem2.dto.LessonDto;
import ru.itis.semwork.lmssystem2.form.LessonForm;

import java.util.List;

public interface LessonService {

    List<LessonDto> getAll();

    LessonDto getById(Long id);

    LessonDto add(LessonForm lessonForm);

    LessonDto edit(Long id, LessonForm lessonForm);

    LessonDto delete(Long id);

}
