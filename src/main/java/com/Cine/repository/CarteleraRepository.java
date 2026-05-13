package com.Cine.repository;

import com.Cine.models.Cartelera;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class CarteleraRepository {

    public CarteleraRepository() {
    }

    // Para que el Admin publique una nueva función en cartelera
    public void addCartelera(Cartelera cartelera) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cartelera);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Para mostrar la lista de funciones disponibles al Admin y al Usuario
    public List<Cartelera> getAllCartelera() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Cartelera> result = entityManager.createQuery("from Cartelera", Cartelera.class).getResultList();
        entityManager.close();
        return result;
    }

    // Crucial para que el Admin busque funciones por película y vea sus reservas
    public List<Cartelera> getCarteleraByPelicula(int idPelicula) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Cartelera> result = entityManager.createQuery(
                        "from Cartelera where idpelicula.idpelicula = :id", Cartelera.class)
                .setParameter("id", idPelicula)
                .getResultList();
        entityManager.close();
        return result;
    }

    // Obtener la cartelera por su ID
    public Cartelera getCarteleraByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cartelera cartelera = entityManager.find(Cartelera.class, id);
        entityManager.close();
        return cartelera;
    }

    // Eliminar
    public void removeCartelera(Cartelera cartelera) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(cartelera));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}