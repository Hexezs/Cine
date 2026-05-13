package com.Cine.repository;

import com.Cine.models.Pelicula;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class PeliculaRepository {

    public PeliculaRepository() {}

    // Admin: Agregar película
    public void addPelicula(Pelicula pelicula) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(pelicula);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Admin: Eliminar por objeto
    public void removePelicula(Pelicula pelicula) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(pelicula));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Para que el Admin busque la película que quiere borrar por su nombre
    public Pelicula getPeliculaByNombre(String nombre) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Pelicula pelicula = entityManager.createQuery(
                            "from Pelicula where nombre = :nombre", Pelicula.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            entityManager.close();
            return pelicula;
        } catch (Exception e) {
            entityManager.close();
            return null;
        }
    }

    public List<Pelicula> getAllPeliculas() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Pelicula> result = entityManager.createQuery("from Pelicula", Pelicula.class).getResultList();
        entityManager.close();
        return result;
    }
}