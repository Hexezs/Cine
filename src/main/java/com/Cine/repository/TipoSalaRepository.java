package com.Cine.repository;

import com.Cine.models.TipoSala;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class TipoSalaRepository {

    public TipoSalaRepository() {
    }

    // Para obtener todos los tipos (ej. VIP, 3D, Tradicional)
    public List<TipoSala> getAllTipos() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<TipoSala> result = entityManager.createQuery("from TipoSala", TipoSala.class).getResultList();
        entityManager.close();
        return result;
    }

    // Para buscar uno específico por su ID
    public TipoSala getTipoByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TipoSala tipo = entityManager.find(TipoSala.class, id);
        entityManager.close();
        return tipo;
    }

    // Agregar
    public void addTipoSala(TipoSala tipoSala) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(tipoSala);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Update
    public void updateTipoSala(TipoSala tipoSala) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(tipoSala);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}