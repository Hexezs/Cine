package com.Cine.repository;

import com.Cine.models.Idioma;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class IdiomaRepository {

    public IdiomaRepository() {
    }

    // Para que el Admin pueda dar de alta nuevos idiomas si fuera necesario
    public void addIdioma(Idioma idioma) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(idioma);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Llenar el ComboBox en la pantalla de "Agregar Película"
    public List<Idioma> getAllIdiomas() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Idioma> result = entityManager.createQuery("from Idioma", Idioma.class).getResultList();
        entityManager.close();
        return result;
    }

    // Para recuperar el objeto Idioma exacto y ponérselo a la Película
    public Idioma getIdiomaByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Idioma idioma = entityManager.find(Idioma.class, id);
        entityManager.close();
        return idioma;
    }

    // Update
    public void updateIdioma(Idioma idioma) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(idioma);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Eliminar
    public void removeIdioma(Idioma idioma) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(idioma));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}