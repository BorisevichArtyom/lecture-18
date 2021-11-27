package by.itacademy.javaenterprise.borisevich.dao;

import by.itacademy.javaenterprise.borisevich.entity.Training;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;

import java.util.List;

public interface TrainingDAO {

    void saveTraining(Training training) throws DAOException;

    Training findTrainingbyId(String id) throws DAOException;

    void updateTraining(Training training) throws DAOException;

    void deleteTraining(String id) throws DAOException;

    List<Training> findAllTrainings() throws DAOException;
}
