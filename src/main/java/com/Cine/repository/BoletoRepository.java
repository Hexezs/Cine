package com.Cine.repository;

import com.Cine.models.Boleto;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class BoletoRepository {

    public BoletoRepository() {
    }

    // Para guardar cada boleto individual de una compra
    public void addBoleto(Boleto boleto) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(boleto);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Para que el Admin vea el detalle de asientos de una reserva
    public List<Boleto> getBoletosByReserva(int idReserva) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Boleto> result = entityManager.createQuery("from Boleto where reserva.idReserva = :id", Boleto.class).setParameter("id", idReserva).getResultList();
        entityManager.close();
        return result;
    }

    // Eliminar
    public void removeBoleto(Boleto boleto) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(boleto));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Boleto getBoletoByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Boleto boleto = entityManager.find(Boleto.class, id);
        entityManager.close();
        return boleto;
    }
}