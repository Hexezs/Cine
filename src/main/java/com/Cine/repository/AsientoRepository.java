package com.Cine.repository;

import com.Cine.models.Asiento;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class AsientoRepository {

    public Asiento buscarAsiento(String letra, String numero, int idSala) {
        EntityManager entityManager = HibernateUtils.getEntityManagerFactory().createEntityManager();
        Asiento asiento = null;

        try {
            asiento = entityManager.createQuery("FROM Asiento a WHERE a.letra = :letra AND a.numero = :numero AND a.idsala.idsala = :idSala",
                            Asiento.class)
                    .setParameter("letra", letra)
                    .setParameter("numero", numero)
                    .setParameter("idSala", idSala)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontró asiento: " + letra + numero + " sala: " + idSala);
        } finally {
            entityManager.close();
        }

        return asiento;
    }
}