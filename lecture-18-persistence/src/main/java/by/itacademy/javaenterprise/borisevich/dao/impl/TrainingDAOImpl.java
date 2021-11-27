package by.itacademy.javaenterprise.borisevich.dao.impl;

import by.itacademy.javaenterprise.borisevich.dao.TrainingDAO;
import by.itacademy.javaenterprise.borisevich.entity.Training;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TrainingDAOImpl implements TrainingDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingDAOImpl.class);
    private static final String SELECT_ALL = "SELECT training_id,training_date,user_id FROM Trainings";

    private final EntityManagerFactory emFactory;

    public TrainingDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveTraining(Training training) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(training);
            training.setId(training.getId());
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("muscle: " + training, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public Training findTrainingbyId(String id) throws DAOException {
        Training training = null;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            training = em.find(Training.class, id);
            if (training == null) {
                throw new DAOException(" Cannot find training by this id:" + id);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return training;
    }

    @Override
    public void updateTraining(Training training) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            if (em.find(Training.class, training.getId()) == null) {
                throw new DAOException("No such training ");
            }
            em.merge(training);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("muscle: " + training, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void deleteTraining(String id) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Training.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Training> findAllTrainings() throws DAOException {
        List<Training> trainings;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            trainings = em.createNativeQuery(SELECT_ALL, Training.class).getResultList();
        } catch (Exception e) {
            throw new DAOException("Problem with find:", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return trainings;
    }
}

