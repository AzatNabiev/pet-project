package ru.itis.semwork.lmssystem2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.semwork.lmssystem2.model.File;
import ru.itis.semwork.lmssystem2.model.enums.FileState;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findByIdAndState(Long id, FileState fileState);

    @Query("select f from File f where f.lesson.id = :lessonId and f.state = :fileState")
    Optional<List<File>> findAllByLessonIdAndState(@Param(value = "lessonId") Long lessonId,
                                                   @Param(value = "fileState") FileState fileState);
}
