package ru.itis.semwork.lmssystem2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.itis.semwork.lmssystem2.model.enums.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserDto {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    private String state;

    private String profilePhoto;

    private String tgAlias;

    private String redisId;

    private LocalDateTime since;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

    private List<LessonDto> lessons;
}
