package com.aerolinea.dao;

import com.aerolinea.entidad.Avion;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component("AvionDaoImpl")
public class AvionDaoImpl extends GenericDaoImpl<Avion, Integer>
        implements AvionDao, Serializable {

   public List<Avion> listarAviones(Integer idavion, Integer capacidad) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = " select v from Avion v "
                        + " where v.idavion>0 ";
            System.out.println(hql);

            if (idavion != 0) {
                hql += " and v.idavion = :idavion ";
            }

            if (capacidad != 0) {
                hql += " and v.capacidad = :capacidad ";
            }
            
            Query query = session.createQuery(hql);
            if (idavion != 0) {
                query.setParameter("idavion", idavion);
            }
            if (capacidad != 0) {
                query.setParameter("capacidad", capacidad);
            }
            List<Avion> entities = query.list();
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
