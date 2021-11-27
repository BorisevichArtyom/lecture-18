package by.itacademy.javaenterprise.borisevich.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class ExerciseDetail {

    @Column(name = "total_repeats")
    private Integer repeats;

    @Column(name = "total_time")
    private LocalTime time;

    @Column(name = "total_weight")
    private Integer weight;

    @Column(name = "total_approaches")
    private Integer approachCounter;


}