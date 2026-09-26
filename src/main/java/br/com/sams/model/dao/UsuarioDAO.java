package br.com.sams.model.dao;

import br.com.sams.model.entity.Usuario;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import static java.util.Objects.requireNonNull;

@Dependent
public class UsuarioDAO {

    private final EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = requireNonNull(em, "em is required");
    }

    public void save(Usuario entity) {
        em.persist(entity);
    }

    public Usuario find(Integer id) {
        return em.find(Usuario.class, id);
    }

    public Usuario findByEmail(String email) {
        try {
            return em.createQuery("from Usuario u where u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
