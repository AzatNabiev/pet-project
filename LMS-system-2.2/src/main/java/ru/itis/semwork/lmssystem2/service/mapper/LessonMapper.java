package ru.itis.semwork.lmssystem2.service.mapper;

import org.springframework.stereotype.Service;
import ru.itis.semwork.lmssystem2.dto.LessonDto;
import ru.itis.semwork.lmssystem2.form.LessonForm;
import ru.itis.semwork.lmssystem2.model.Lesson;
import ru.itis.semwork.lmssystem2.model.enums.LessonState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonMapper {

    public Lesson mapToLesson(LessonForm lessonForm) {
        return Lesson.builder()
                     .name(lessonForm.getName())
                     .description(lessonForm.getDescription())
                     .creatorId(lessonForm.getCreatorId())
                     .start(LocalDateTime.now())
                     .state(LessonState.ACTIVE)
                     .build();
    }

    public LessonDto mapToLessonDto(Lesson lesson) {
        return LessonDto.builder()
                        .id(lesson.getId())
                        .name(lesson.getName())
                        .description(lesson.getDescription())
                        .start(lesson.getStart())
                        .creatorId(lesson.getCreatorId())
                        .build();
    }

    public List<LessonDto> mapToLessonDto(List<Lesson> lessons) {
        return lessons.stream()
                      .map(this::mapToLessonDto)
                      .collect(Collectors.toList());
    }

}
