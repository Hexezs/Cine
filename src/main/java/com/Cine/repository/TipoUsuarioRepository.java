package com.Cine.repository;

import com.Cine.models.TipoUsuario;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class TipoUsuarioRepository {

    public TipoUsuarioRepository() {}

    public TipoUsuario getTipoByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, id);
        entityManager.close();
        return tipoUsuario;
    }
    public List<TipoUsuario> getAllTipos() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<TipoUsuario> result = entityManager.createQuery("from TipoUsuario", TipoUsuario.class).getResultList();
        entityManager.close();
        return result;
    }
}