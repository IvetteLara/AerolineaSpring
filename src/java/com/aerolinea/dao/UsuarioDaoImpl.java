package com.aerolinea.dao;

import com.aerolinea.entidad.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component("UsuarioDaoImpl")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer>
        implements UsuarioDao, Serializable {

    
    public List<Usuario> listarUsuario() throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = " select u from Usuario u join fetch u.rol "
                    + " join fetch u.pais ";
            System.out.println(hql);

            Query query = session.createQuery(hql);

            List<Usuario> entities = query.list();
            session.getTransaction().commit();
            return entities;
        } catch (Exception ex) {
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } catch (Exception exc) {
            }
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

}
