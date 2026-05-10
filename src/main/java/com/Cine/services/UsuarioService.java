package com.Cine.services;
//repository call
import com.Cine.dto.UsuarioRegistroDTO;
import com.Cine.models.Usuario;
import com.Cine.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import com.Cine.dto.ReservaRegistroDTO;
import com.Cine.dto.BoletoDTO;
import java.util.List;

public class UsuarioService {
    private EntityManagerFactory emf;

    public UsuarioService() {
        //inicializa una sola vez cuando se crea el servicio
        this.emf = HibernateUtils.getEntityManagerFactory();
    }

    public void registrarNuevoUsuario(UsuarioRegistroDTO dto) throws Exception{
        EntityManager em = emf.createEntityManager();

        //quitamos espacios y pasamos a minusculas
        String correoLimpio = dto.correo().trim().toLowerCase();
        String nombreLimpio = dto.nombre().trim();
        String apellidoLimpio = dto.apellidoM().trim();

        //Mapeo -> MAPPER
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombreLimpio);
        nuevoUsuario.setApellidoM(apellidoLimpio);
        nuevoUsuario.setCorreo(correoLimpio);
        nuevoUsuario.setPassword(dto.password());

        //asignar tipo de usuario por defecto: ej. cliente
        nuevoUsuario.setTipoUsuario_idtipoUsuario(2);

        //guardar en bd
        try {
            em.getTransaction().begin();
            em.persist(nuevoUsuario);
            em.getTransaction().commit();
            System.out.println("Usuario " + correoLimpio + " registrado con exito");
        } catch (Exception e) {
            em.getTransaction().rollback(); //si algo sale mal, cancela la transaccion
            throw new Exception("Error al guardar usuario: " + e.getMessage());
        } finally { //asegura que:
            em.close(); //siempre se cierre el EntityManager incluso si ocurre un error
        }
    }

//    public void addUser(Usuario usuario) {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(usuario);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }

    public List<Usuario> getAllUsers() {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<Usuario> result = entityManager.createQuery( "from Usuario", Usuario.class ).getResultList();
//        entityManager.close();
//        return result;
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("from Usuario", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario getUserByID(int id) {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Usuario usuario = entityManager.find(Usuario.class, id);
//        return usuario;
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public void updateUser(Usuario usuario) {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.merge(usuario);
//        entityManager.getTransaction().commit();
//        entityManager.close();
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removeUser(int id) {
//        EntityManagerFactory entityManagerFactory = HibernateUtils.getEntityManagerFactory();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.remove(entityManager.merge(usuario));
//        entityManager.getTransaction().commit();
//        entityManager.close();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
