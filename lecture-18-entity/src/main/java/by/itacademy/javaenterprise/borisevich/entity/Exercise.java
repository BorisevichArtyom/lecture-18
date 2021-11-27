package by.itacademy.javaenterprise.borisevich.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Exercises_name", indexes = {
        @Index(name = "Exercise_name_UNIQUE", columnList = "name", unique = true)
})
@SecondaryTable(name = "Exercises_on_training")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_exercises_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "exerciseSet")
    private Set<Muscle> muscleSet = new HashSet<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "repeats", column = @Column(name = "total_repeats")),
    })
    private ExerciseDetail exerciseOnTraining;


}
