package ru.itis.semwork.lmssystem2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semwork.lmssystem2.dto.FileDto;
import ru.itis.semwork.lmssystem2.service.FileService;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{id}")
    @Operation(summary = "Getting the files related to lesson")
    public ResponseEntity<?> getByLessonId(@PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(fileService.retrieveByLessonId(id));
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Adding the file at lesson")
    public ResponseEntity<FileDto> addToLesson(@PathVariable(name = "id") Long lessonId,
                                               @RequestParam(name = "file") @Parameter(description = "File to upload", required = true,
                                                       schema = @Schema(type = "string", format = "binary")) MultipartFile file) {
        return ResponseEntity.ok(fileService.addToLesson(lessonId, file.getOriginalFilename(), file.getContentType(), file));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting the file")
    public ResponseEntity<FileDto> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(fileService.delete(id));
    }
}
