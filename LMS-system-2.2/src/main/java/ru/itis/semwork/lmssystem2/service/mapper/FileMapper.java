package ru.itis.semwork.lmssystem2.service.mapper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semwork.lmssystem2.dto.FileDto;
import ru.itis.semwork.lmssystem2.model.File;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileMapper {

    public List<FileDto> mapToFileDto(List<File> files) {
        return files.stream().map(this::mapToFileDto).collect(Collectors.toList());
    }

    public FileDto mapToFileDto(File file) {
        return FileDto.builder()
                      .id(file.getId())
                      .name(file.getName())
                      .file(file.getFile())
                      .format(file.getFormat())
                      .state(file.getState().toString())
                      .build();
    }

    public File mapToFile(MultipartFile file, String fileName, String contentType) {
        LocalDateTime now = LocalDateTime.now();
        byte[] fileBytes;
        try {
            fileBytes = file.getBytes();
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot get bytes from file");
        }

        return File.builder()
                   .name(fileName)
                   .format(contentType)
                   .file(fileBytes)
                   .createDate(now)
                   .updateDate(now)
                   .build();
    }

}
