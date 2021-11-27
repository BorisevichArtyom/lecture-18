package by.itacademy.javaenterprise.borisevich.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class Training {
    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "train"),
            strategy = "by.itacademy.javaenterprise.borisevich.generatorid.TrainingIdGenerator")
    @Column(name = "training_id")
    private String id;

    @Column(name = "training_date")
    private Instant trainingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private DiaryUser user;

    @Transient
    private String name;

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingDate=" + trainingDate +
                ", transientname= " + name+
                ", user=" + user +
                + '\'' +
                '}';
    }
}