package ru.itis.semwork.lmssystem2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.semwork.lmssystem2.model.Lesson;
import ru.itis.semwork.lmssystem2.model.enums.LessonState;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Optional<List<Lesson>> findAllByState(LessonState lessonState);

    Optional<Lesson> findByIdAndState(Long id, LessonState lessonState);
}
