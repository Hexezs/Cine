package com.Cine.repository;
import com.Cine.models.Sala;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class SalaRepository {

    public SalaRepository() {
    }

    // Para que el Admin vea la lista de salas en un ComboBox al programar una función
    public List<Sala> getAllSalas() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Sala> result = entityManager.createQuery("from Sala", Sala.class).getResultList();
        entityManager.close();
        return result;
    }

    // Para jalar los datos de una sala (como la capacidad) cuando el Admin la selecciona
    public Sala getSalaByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Sala sala = entityManager.find(Sala.class, id);
        entityManager.close();
        return sala;
    }
}