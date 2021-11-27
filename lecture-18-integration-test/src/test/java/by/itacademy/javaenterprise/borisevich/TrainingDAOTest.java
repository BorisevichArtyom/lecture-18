package by.itacademy.javaenterprise.borisevich;

import by.itacademy.javaenterprise.borisevich.dao.TrainingDAO;
import by.itacademy.javaenterprise.borisevich.dao.impl.TrainingDAOImpl;
import by.itacademy.javaenterprise.borisevich.entity.DiaryUser;
import by.itacademy.javaenterprise.borisevich.entity.Training;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingDAOTest.class);

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("test");
    private Training training = Training.builder().trainingDate(Instant.now()).user(DiaryUser.builder().id(1L).build()).build();
    private TrainingDAO trainingDAO = new TrainingDAOImpl(emFactory);

    @Test
    public void testFindAllTrainings() throws DAOException {
        assertNotNull(trainingDAO.findAllTrainings());
    }

    @Test
    public void testFindTrainingDateById() throws DAOException {
        trainingDAO.saveTraining(training);
        assertNotNull(trainingDAO.findTrainingbyId(training.getId()));
        assertThrows(DAOException.class, () -> trainingDAO.findTrainingbyId("0"));
        trainingDAO.deleteTraining(training.getId());
    }

    @Test
    public void testSaveTraining() throws DAOException {
        trainingDAO.saveTraining(training);
        assertEquals(training.getId(), trainingDAO.findTrainingbyId(training.getId()).getId());
        trainingDAO.deleteTraining(training.getId());

    }

    @Test
    public void testSaveTrainingNull() {
        assertThrows(DAOException.class, () -> trainingDAO.saveTraining(new Training()));
    }

    @Test
    public void testSaveAndGetIdTraining() throws DAOException {
        trainingDAO.saveTraining(training);
        assertNotNull(trainingDAO.findTrainingbyId(training.getId()));
        trainingDAO.deleteTraining(training.getId());
    }

    @Test
    public void testSaveTrainingConflict() throws DAOException {
        trainingDAO.saveTraining(training);
        assertThrows(DAOException.class, () -> trainingDAO.saveTraining(training));
        trainingDAO.deleteTraining(training.getId());
    }

    @Test//ToDO fck update
    public void testUpdateTraining() throws DAOException {
        trainingDAO.saveTraining(training);
        training.setTrainingDate(Instant.EPOCH);
        trainingDAO.updateTraining(training);

    }

    @Test
    public void testUpdateTrainingInvalid() throws DAOException {
        assertThrows(DAOException.class, () -> {
            trainingDAO.updateTraining(new Training());
        });
    }

    @Test
    public void testDeleteTraining() throws DAOException {
        trainingDAO.saveTraining(training);
        trainingDAO.deleteTraining(training.getId());
        assertThrows(DAOException.class, () -> trainingDAO.findTrainingbyId(training.getId()));
    }
}
