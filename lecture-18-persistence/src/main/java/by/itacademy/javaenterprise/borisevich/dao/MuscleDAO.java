package by.itacademy.javaenterprise.borisevich.dao;


import by.itacademy.javaenterprise.borisevich.entity.Muscle;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;

import java.util.List;

public interface MuscleDAO {

    void saveMuscle(Muscle muscle) throws DAOException;

    Muscle findMusclebyId(Long id) throws DAOException;

    void updateMuscle(Muscle muscle) throws DAOException;

    void deleteMuscle(Long id) throws DAOException;

    List<Muscle> findAllMuscles() throws DAOException;
}
