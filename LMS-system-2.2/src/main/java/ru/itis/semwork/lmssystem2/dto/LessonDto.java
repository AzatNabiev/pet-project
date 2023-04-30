package ru.itis.semwork.lmssystem2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class LessonDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime start;
    private Long creatorId;
}
