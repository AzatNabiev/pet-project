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
public class UserForm {
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String role;

    private String profilePhoto;

    private String tgAlias;

    private String redisId;

    private LocalDateTime since;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
