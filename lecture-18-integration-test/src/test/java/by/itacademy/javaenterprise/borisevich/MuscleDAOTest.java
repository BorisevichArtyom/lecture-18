package by.itacademy.javaenterprise.borisevich;

import by.itacademy.javaenterprise.borisevich.dao.MuscleDAO;
import by.itacademy.javaenterprise.borisevich.dao.impl.MuscleDAOImpl;
import by.itacademy.javaenterprise.borisevich.entity.Muscle;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class MuscleDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MuscleDAOTest.class);

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("test");
    private Muscle muscle = Muscle.builder().muscleName("smth").build();
    private MuscleDAO muscleDAO = new MuscleDAOImpl(emFactory);

    @Test
    public void testFindAllMuscles() throws DAOException {
        assertNotNull(muscleDAO.findAllMuscles());
    }


    @Test
    public void testFindMuscleNameById() throws DAOException {
        assertNotNull(muscleDAO.findMusclebyId(1L));
        assertThrows(DAOException.class, () -> muscleDAO.findMusclebyId(0L));
    }

    @Test
    public void testSaveMuscle() throws DAOException {
        muscleDAO.saveMuscle(muscle);
        assertEquals(muscle.getMuscleName(), muscleDAO.findMusclebyId(muscle.getId()).getMuscleName());
        muscleDAO.deleteMuscle(muscle.getId());

    }

    @Test
    public void testSaveMuscleNull() {
        assertThrows(DAOException.class, () -> muscleDAO.saveMuscle(new Muscle()));
    }

    @Test
    public void testSaveAndGetIdMuscle() throws DAOException {
        muscleDAO.saveMuscle(muscle);
        assertNotNull(muscleDAO.findMusclebyId(muscle.getId()));
        muscleDAO.deleteMuscle(muscle.getId());
    }

    @Test
    public void testSaveMuscleConflict() throws DAOException {
        muscleDAO.saveMuscle(muscle);
        assertThrows(DAOException.class, () -> muscleDAO.saveMuscle(muscle));
        muscleDAO.deleteMuscle(muscle.getId());
    }

    @Test
    public void testUpdateMuscle() throws DAOException {
        muscleDAO.saveMuscle(muscle);
        muscle.setMuscleName("Brachialis");
        muscleDAO.updateMuscle(muscle);
        assertEquals("Brachialis", muscleDAO.findMusclebyId(muscle.getId()).getMuscleName());
        muscleDAO.deleteMuscle(muscle.getId());
    }

    @Test
    public void testUpdateMuscleInvalid() throws DAOException {
        assertThrows(DAOException.class, () -> {
            muscleDAO.updateMuscle(new Muscle());
        });
    }

    @Test
    public void testDeleteMuscle() throws DAOException {
        muscleDAO.saveMuscle(muscle);
        muscleDAO.deleteMuscle(muscle.getId());
        assertThrows(DAOException.class, () -> muscleDAO.findMusclebyId(muscle.getId()));
    }

}
