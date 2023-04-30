package ru.itis.semwork.lmssystem2.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class LessonForm {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private Long creatorId;
}
