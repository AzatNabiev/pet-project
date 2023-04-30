package ru.itis.semwork.lmssystem2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import ru.itis.semwork.lmssystem2.model.enums.LessonState;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "lessons")
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime start;
    private Long creatorId;

    @Enumerated(EnumType.STRING)
    private LessonState state;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "lesson")
    @JsonManagedReference
    @ToString.Exclude
    private List<File> files;

    @ManyToMany(mappedBy = "lessons")
    @ToString.Exclude
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return name.equals(lesson.name) && description.equals(lesson.description) && start.equals(lesson.start) && creatorId.equals(
                lesson.creatorId) && state == lesson.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, start, creatorId, state);
    }
}
