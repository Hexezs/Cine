package com.Cine.repository;

import com.Cine.dto.CarteleraDTO;
import com.Cine.models.Cartelera;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
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

    // Para mostrar la lista de funciones disponibles
    public List<Cartelera> getAllCartelera() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Cartelera> result = entityManager.createQuery(
                "from Cartelera",
                Cartelera.class
        ).getResultList();
        entityManager.close();
        return result;
    }

    // Buscar funciones por película
    public List<Cartelera> getCarteleraByPelicula(int idPelicula) {

        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Cartelera> result = entityManager.createQuery(
                        "from Cartelera where idpelicula.idpelicula = :id",
                        Cartelera.class
                )
                .setParameter("id", idPelicula)
                .getResultList();

        entityManager.close();
        return result;
    }

    // Obtener cartelera por ID
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
    public List<Cartelera> getFuncionesPorSalaYFecha(int idSala, LocalDate fecha) {

        EntityManager entityManager = HibernateUtils.getEntityManagerFactory().createEntityManager();

        List<Cartelera> result = entityManager.createQuery(
                        "FROM Cartelera c WHERE c.idsala.idsala = :idSala AND c.fecha = :fecha",
                        Cartelera.class
                )
                .setParameter("idSala", idSala)
                .setParameter("fecha", fecha)
                .getResultList();

        entityManager.close();
        return result;
    }
    public List<Cartelera> getFuncionesPorPelicula(int idPelicula) {

        EntityManager em =
                HibernateUtils.getEntityManagerFactory()
                        .createEntityManager();

        List<Cartelera> lista = em.createQuery(
                        "FROM Cartelera c WHERE c.idpelicula.idpelicula = :id",
                        Cartelera.class
                )
                .setParameter("id", idPelicula)
                .getResultList();

        em.close();

        return lista;
    }
}