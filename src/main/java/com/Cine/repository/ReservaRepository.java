package com.Cine.repository;

import com.Cine.models.Reserva;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class ReservaRepository {

    public ReservaRepository() {
    }

    // Para cuando el usuario finaliza su compra
    public void addReserva(Reserva reserva) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(reserva);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Reserva> getReservasByCartelera(int idCartelera) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Reserva> result = entityManager.createQuery(
                        "from Reserva where idcartelera.idCartelera = :id",
                        Reserva.class)
                .setParameter("id", idCartelera)
                .getResultList();
        entityManager.close();
        return result;
    }

    // Para la opción de "Cancelar Reserva" del Admin
    public void removeReserva(Reserva reserva) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // Al usar cascade = CascadeType.ALL en el modelo,
        // borrar la reserva borrará automáticamente sus boletos.
        entityManager.remove(entityManager.merge(reserva));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Reserva> getAllReservas() {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Reserva> result = entityManager.createQuery("from Reserva", Reserva.class).getResultList();
        entityManager.close();
        return result;
    }
    public void eliminarReservasPorCartelera(int idCartelera) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Reserva> reservas = entityManager.createQuery("from Reserva where idcartelera.idCartelera = :id", Reserva.class).setParameter("id", idCartelera).getResultList();
        for (Reserva reserva : reservas) {
            entityManager.remove(entityManager.merge(reserva));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Reserva> getReservasByUsuario(int idUsuario) {

        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Reserva> result = entityManager.createQuery(
                        "from Reserva where idusuario.idusuario = :id",
                        Reserva.class)
                .setParameter("id", idUsuario)
                .getResultList();

        entityManager.close();
        return result;
    }
}