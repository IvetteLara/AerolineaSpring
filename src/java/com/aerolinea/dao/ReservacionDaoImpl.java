package com.aerolinea.dao;

import com.aerolinea.entidad.Reservacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component("ReservacionDaoImpl")
public class ReservacionDaoImpl extends GenericDaoImpl<Reservacion, Integer>
        implements ReservacionDao, Serializable {

    
    public List<Reservacion> listarReservaciones(String idusuario, Integer idvuelo, Date fecha,Integer nboletos, BigDecimal precio) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = " select r from Reservacion r join fetch r.usuario "
                    + " join fetch r.vuelo "
                    + " where r.idreservacion>0 ";
            System.out.println(hql);

            if (idusuario != null) {
                hql += " and r.usuario.idusuario = :idusuario ";
            }

            if (idvuelo != 0) {
                hql += " and r.vuelo.idvuelo = :idvuelo ";
            }            
            
            if (fecha != null) {
                hql += " and r.fecha = :fecha ";
            }            

            if (nboletos != 0) {
                hql += " and r.nboletos = :nboletos ";
            } 
            
            if (precio.doubleValue() != 0) {
                hql += " and r.precio = :precio ";
            }            

            Query query = session.createQuery(hql);
            if (idusuario != null) {
                query.setParameter("idusuario", idusuario);
            }
            if (idvuelo != 0) {
                query.setParameter("idvuelo", idvuelo);
            }
            if (fecha != null) {
                query.setParameter("fecha", fecha);
            }              
            if (nboletos != 0) {
                query.setParameter("nboletos", nboletos);
            } 
            if (precio.doubleValue() != 0) {
                query.setParameter("precio", precio);
            }  

            List<Reservacion> entities = query.list();
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
            //session.close();
        }
    }

}
