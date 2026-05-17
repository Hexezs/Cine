package com.Cine.repository;

import com.Cine.models.Asiento;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class AsientoRepository {

    public AsientoRepository() {
    }

    // Para mostrar todos los asientos de la sala
    public List<Asiento> getAsientosBySala(int idSala) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Asiento> result = entityManager.createQuery("from Asiento where sala.idSala = :idSala", Asiento.class).setParameter("idSala", idSala).getResultList();
        entityManager.close();
        return result;
    }


    // Esto evita que el usuario escoja uno que ya se vendió
    public List<Asiento> getAsientosOcupados(int idCartelera) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Buscamos asientos que ya estén en una reserva para esa función de cartelera
        List<Asiento> ocupados = entityManager.createQuery("select r.asiento from Reserva r where r.cartelera.idCartelera = :id", Asiento.class).setParameter("id", idCartelera).getResultList();
        entityManager.close();
        return ocupados;
    }

    public Asiento getAsientoByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Asiento asiento = entityManager.find(Asiento.class, id);
        entityManager.close();
        return asiento;
    }

    // Estilo profe: persistir un asiento nuevo
    public void addAsiento(Asiento asiento) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(asiento);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}