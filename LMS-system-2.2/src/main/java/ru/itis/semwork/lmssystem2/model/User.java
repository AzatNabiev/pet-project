package ru.itis.semwork.lmssystem2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semwork.lmssystem2.model.enums.UserRole;
import ru.itis.semwork.lmssystem2.model.enums.UserState;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String redisId;

    @Enumerated(EnumType.STRING)
    private UserState state;

    @Column(length = 8000)
    private String profilePhoto;

    private String tgAlias;

    private LocalDateTime since;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

    @ManyToMany
    @JoinTable(name = "user_lesson_assn",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"))
    private List<Lesson> lessons;
}
