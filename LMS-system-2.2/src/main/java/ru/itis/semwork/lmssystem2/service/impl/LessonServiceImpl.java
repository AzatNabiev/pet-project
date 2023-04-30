package ru.itis.semwork.lmssystem2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semwork.lmssystem2.dto.LessonDto;
import ru.itis.semwork.lmssystem2.form.LessonForm;
import ru.itis.semwork.lmssystem2.model.Lesson;
import ru.itis.semwork.lmssystem2.model.User;
import ru.itis.semwork.lmssystem2.model.enums.LessonState;
import ru.itis.semwork.lmssystem2.model.enums.UserRole;
import ru.itis.semwork.lmssystem2.repository.LessonRepository;
import ru.itis.semwork.lmssystem2.repository.UserRepository;
import ru.itis.semwork.lmssystem2.service.LessonService;
import ru.itis.semwork.lmssystem2.service.mapper.LessonMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final LessonMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<LessonDto> getAll() {
        List<Lesson> lessons = lessonRepository.findAllByState(LessonState.ACTIVE)
                                               .orElse(new ArrayList<>());
        return mapper.mapToLessonDto(lessons);
    }

    @Override
    @Transactional(readOnly = true)
    public LessonDto getById(Long id) {
        Lesson lesson = lessonRepository.findByIdAndState(id, LessonState.ACTIVE)
                                        .orElseThrow(() -> new IllegalStateException("Cannot find by id"));

        return mapper.mapToLessonDto(lesson);
    }

    @Override
    @Transactional
    public LessonDto add(LessonForm lessonForm) {
        User user = validCreatorId(lessonForm.getCreatorId());

        Lesson lesson = mapper.mapToLesson(lessonForm);

        lesson = lessonRepository.save(lesson);

        return mapper.mapToLessonDto(lesson);
    }

    @Override
    @Transactional
    public LessonDto edit(Long id, LessonForm lessonForm) {
        Lesson lesson = lessonRepository.findById(id)
                                        .orElseThrow(() -> new IllegalStateException("Cannot find by id"));

        lesson.setName(lessonForm.getName());
        lesson.setDescription(lessonForm.getDescription());
        lesson.setStart(lessonForm.getStart());

        lesson = lessonRepository.save(lesson);

        return mapper.mapToLessonDto(lesson);
    }

    @Override
    @Transactional
    public LessonDto delete(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                                        .orElseThrow(() -> new IllegalStateException("Cannot find by id"));
        lesson.setState(LessonState.ARCHIVED);

        lesson = lessonRepository.save(lesson);
        return mapper.mapToLessonDto(lesson);
    }

    private User validCreatorId(Long creatorId) {
        User user = userRepository.findById(creatorId)
                .orElseThrow(() -> new IllegalStateException("User doesnt exist"));

        if (!user.getRole().equals(UserRole.TEACHER)) {
            throw new IllegalStateException("Student cannot create a lesson");
        }

        return user;
    }
}
