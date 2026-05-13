package com.Cine.repository;

import com.Cine.models.ClasificacionRTC;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ClasificacionRTCRepository {

    public ClasificacionRTCRepository() {
    }

    // Para que el Admin pueda agregar nuevas clasificaciones (ej: AA, A, B15)
    public void addClasificacion(ClasificacionRTC clasificacion) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(clasificacion);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // ComboBox en la pantalla de "Agregar Película" del Admin
    public List<ClasificacionRTC> getAllClasificaciones() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ClasificacionRTC> result = entityManager.createQuery("from ClasificacionRTC", ClasificacionRTC.class).getResultList();
        entityManager.close();
        return result;
    }

    // Para obtener el objeto y asociarlo a la Película al momento de guardar
    public ClasificacionRTC getClasificacionByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ClasificacionRTC clasificacion = entityManager.find(ClasificacionRTC.class, id);
        entityManager.close();
        return clasificacion;
    }

    // Update
    public void updateClasificacion(ClasificacionRTC clasificacion) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(clasificacion);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Eliminar
    public void removeClasificacion(ClasificacionRTC clasificacion) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(clasificacion));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}