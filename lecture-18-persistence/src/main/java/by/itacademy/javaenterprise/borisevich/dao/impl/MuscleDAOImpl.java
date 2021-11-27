package by.itacademy.javaenterprise.borisevich.dao.impl;


import by.itacademy.javaenterprise.borisevich.dao.MuscleDAO;
import by.itacademy.javaenterprise.borisevich.entity.Muscle;
import by.itacademy.javaenterprise.borisevich.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class MuscleDAOImpl implements MuscleDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MuscleDAOImpl.class);
    private static final String SELECT_ALL = "SELECT muscle_id,muscle_name FROM Muscles";

    private final EntityManagerFactory emFactory;

    public MuscleDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveMuscle(Muscle muscle) throws DAOException {
        EntityManager em = null;
        try {
            em= emFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(muscle);
            muscle.setId(muscle.getId());
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("muscle: " + muscle, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Muscle findMusclebyId(Long id) throws DAOException {
        Muscle muscle = null;
        EntityManager em = null;
        try {
            em= emFactory.createEntityManager();
            em.getTransaction().begin();
            muscle = em.find(Muscle.class, id);
            if (muscle == null) {
                throw new DAOException(" Cannot find muscle by this id:" + id);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return muscle;
    }

    @Override
    public void updateMuscle(Muscle muscle) throws DAOException {
        EntityManager em = null;
        try {
            em= emFactory.createEntityManager();
            em.getTransaction().begin();
            if (em.find(Muscle.class, muscle.getId()) == null) {
                throw new DAOException("No such muscle ");
            }
            em.merge(muscle);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("muscle: " + muscle, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void deleteMuscle(Long id) throws DAOException {
        EntityManager em = null;
        try {
            em= emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Muscle.class, id));
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
    public List<Muscle> findAllMuscles() throws DAOException {
        List<Muscle> muscles;
        EntityManager em = null;
        try {
            em= emFactory.createEntityManager();
            muscles = em.createNativeQuery(SELECT_ALL, Muscle.class).getResultList();
        } catch (Exception e) {
            throw new DAOException("Problem with find:", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return muscles;
    }

}
