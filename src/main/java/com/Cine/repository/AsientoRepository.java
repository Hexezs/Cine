package com.Cine.repository;

import com.Cine.models.Asiento;
import com.Cine.utils.HibernateUtils;

import jakarta.persistence.EntityManager;

public class AsientoRepository {

    public Asiento buscarAsiento(String letra, String numero, int idSala) {
        EntityManager entityManager = HibernateUtils.getEntityManagerFactory().createEntityManager();
        Asiento asiento = entityManager.createQuery("from Asiento where letra = :letra " + "and numero = :numero " + "and idsala.idsala = :idSala", Asiento.class)
                .setParameter("letra", letra)
                .setParameter("numero", numero)
                .setParameter("idSala", idSala)
                .getSingleResult();
        entityManager.close();
        return asiento;
    }
}