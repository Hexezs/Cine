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
        entityManager.createQuery("""
                DELETE FROM Cartelera c
                WHERE c.idpelicula.idpelicula = :id
                """)
                .setParameter("id", pelicula.getIdpelicula()).executeUpdate();
        entityManager.remove(entityManager.merge(pelicula));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Buscar por nombre
    public Pelicula getPeliculaByNombre(String nombre) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Pelicula pelicula = entityManager.createQuery("FROM Pelicula WHERE nombre = :nombre", Pelicula.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            entityManager.close();
            return pelicula;
        } catch (Exception e) {
            entityManager.close();
            return null;
        }
    }
    public List<Pelicula> getPeliculasConCartelera() {

        EntityManager em = HibernateUtils.getEntityManagerFactory().createEntityManager();

        List<Pelicula> result = em.createQuery(
                "SELECT DISTINCT p FROM Pelicula p JOIN Cartelera c ON c.idpelicula.idpelicula = p.idpelicula",
                Pelicula.class
        ).getResultList();

        em.close();
        return result;
    }
    // Buscar por ID
    public Pelicula getPeliculaByID(int id){
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Pelicula pelicula = entityManager.find(Pelicula.class, id);
        entityManager.close();
        return pelicula;
    }
    // Obtener todas
    public List<Pelicula> getAllPeliculas() {
        EntityManagerFactory entityManagerFactory =HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Pelicula> result = entityManager.createQuery(
                "FROM Pelicula",
                Pelicula.class
        ).getResultList();
        entityManager.close();
        return result;
    }
}