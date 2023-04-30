package ru.itis.semwork.lmssystem2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semwork.lmssystem2.dto.FileDto;
import ru.itis.semwork.lmssystem2.model.File;
import ru.itis.semwork.lmssystem2.model.Lesson;
import ru.itis.semwork.lmssystem2.model.enums.FileState;
import ru.itis.semwork.lmssystem2.model.enums.LessonState;
import ru.itis.semwork.lmssystem2.repository.FileRepository;
import ru.itis.semwork.lmssystem2.repository.LessonRepository;
import ru.itis.semwork.lmssystem2.service.FileService;
import ru.itis.semwork.lmssystem2.service.mapper.FileMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final LessonRepository lessonRepository;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Override
    @Transactional
    public FileDto addToLesson(Long lessonId, String fileName, String contentType, MultipartFile multipartFile) {
        File file = fileMapper.mapToFile(multipartFile, fileName, contentType);

        Lesson lesson = lessonRepository.findByIdAndState(lessonId, LessonState.ACTIVE)
                .orElseThrow(() -> new IllegalStateException("Cannot find the lesson by id"));

        List<File> files = lesson.getFiles();

        if (files == null) {
            files = new ArrayList<>();
        }

        files.add(file);
        file.setLesson(lesson);
        file.setState(FileState.ACTIVE);

        file = fileRepository.save(file);

        return fileMapper.mapToFileDto(file);
    }

    @Override
    @Transactional
    public FileDto delete(Long id) {

        File file = fileRepository.findByIdAndState(id, FileState.ACTIVE)
                .orElseThrow(() -> new IllegalStateException("Cannot find an ACTIVE file"));

        file.setState(FileState.ARCHIVED);

        fileRepository.save(file);

        return fileMapper.mapToFileDto(file);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileDto> retrieveByLessonId(Long lessonId) {
        List<File> files = fileRepository.findAllByLessonIdAndState(lessonId, FileState.ACTIVE)
                .orElse(new ArrayList<>());
        return fileMapper.mapToFileDto(files);
    }
}
