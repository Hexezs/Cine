package com.Cine.repository;

import com.Cine.models.Usuario;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UsuarioRepository {

    public UsuarioRepository() {
    }
    public void addUser(Usuario usuario) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Usuario login(String correo, String password) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Usuario usuario = entityManager.createQuery("from Usuario where correo = :correo and password = :password", Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("password", password)
                    .getSingleResult();
            entityManager.close();
            return usuario;
        } catch (Exception e) {
            entityManager.close();
            return null;
        }
    }

    public boolean existeCorreo(String correo) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Usuario> result = entityManager.createQuery("from Usuario where correo = :correo", Usuario.class)
        .setParameter("correo", correo).getResultList();
        entityManager.close();
        return !result.isEmpty();
    }

    public void updateUser(Usuario usuario) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void removeUser(Usuario usuario) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Usuario user = entityManager.merge(usuario);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Usuario getUserByID(int id) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.close();
        return usuario;
    }
//    public List<Usuario> getAllUsuarios() {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Usuario> usuarios = entityManager.createQuery("from Usuario", Usuario.class).getResultList();
//        entityManager.close();
//        return usuarios;
//    }
//    public List<Usuario> getUsuariosByFuncion(int idFuncion) {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Usuario> usuarios = entityManager.createQuery("select b.idusuario " + "from Boleto b " + "where b.idcartelera.idCartelera = :idFuncion", Usuario.class).setParameter("idFuncion", idFuncion).getResultList();
//        entityManager.close();
//        return usuarios;
//    }
}