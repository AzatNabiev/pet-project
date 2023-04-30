package ru.itis.semwork.lmssystem2.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.semwork.lmssystem2.dto.FileDto;
import ru.itis.semwork.lmssystem2.form.FileForm;

import java.util.List;

public interface FileService {

    FileDto addToLesson(Long lessonId, String fileName, String contentType, MultipartFile multipartFile);

    FileDto delete(Long id);

    List<FileDto> retrieveByLessonId(Long lessonId);
}
