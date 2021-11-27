package by.itacademy.javaenterprise.borisevich;

import by.itacademy.javaenterprise.borisevich.dao.MuscleDAO;
import by.itacademy.javaenterprise.borisevich.dao.TrainingDAO;
import by.itacademy.javaenterprise.borisevich.dao.impl.MuscleDAOImpl;
import by.itacademy.javaenterprise.borisevich.dao.impl.TrainingDAOImpl;
import by.itacademy.javaenterprise.borisevich.entity.DiaryUser;
import by.itacademy.javaenterprise.borisevich.entity.Training;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Instant;

public class Main {

    public static void main(String[] args) throws DAOException {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("by.it");
        MuscleDAO muscleDAO = new MuscleDAOImpl(emFactory);
        muscleDAO.findAllMuscles();


        TrainingDAO trainingDAO = new TrainingDAOImpl(emFactory);

        trainingDAO.findAllTrainings();
        Training training =Training.builder().trainingDate(Instant.now()).user(DiaryUser.builder().id(1L).build()).build();
        trainingDAO.saveTraining(training);

    }
}
