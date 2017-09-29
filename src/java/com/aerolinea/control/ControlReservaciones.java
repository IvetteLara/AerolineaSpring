package com.aerolinea.control;

import com.aerolinea.dao.ReservacionDaoImpl;
import com.aerolinea.dao.UsuarioDaoImpl;
import com.aerolinea.dao.VueloDaoImpl;
import com.aerolinea.entidad.Reservacion;
import com.aerolinea.entidad.Usuario;
import com.aerolinea.entidad.Vuelo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controlReservaciones")
@SessionScoped
public class ControlReservaciones implements Serializable {

    private Vuelo vuelo;
    private Usuario usuario;
    private Reservacion reservacion;
    
    private List<Vuelo> vuelos;
    private List<Usuario> usuarios;
    private List<Reservacion> reservaciones;
        
    @ManagedProperty("#{VueloDaoImpl}")
    private VueloDaoImpl vueloDaoImpl;
    @ManagedProperty("#{UsuarioDaoImpl}")
    private UsuarioDaoImpl usuarioDaoImpl;
    @ManagedProperty("#{ReservacionDaoImpl}")
    private ReservacionDaoImpl reservacionDaoImpl;

    //busqueda
    private String idusuario;
    private Integer idvuelo;
    private Date fecha;
    private Integer nboletos = 0;
    private BigDecimal precio = BigDecimal.ZERO;

    
    @PostConstruct
    public void init() {
        try {
            vuelo = vueloDaoImpl.create();
            usuario = usuarioDaoImpl.create();
            reservacion = reservacionDaoImpl.create();
            
        } catch (Exception e) {
        }
    }

    public ControlReservaciones() {
    }

    public String preparaNuevo() {
        try {
            vuelo = vueloDaoImpl.create();
            usuario = usuarioDaoImpl.create();
            reservacion = reservacionDaoImpl.create();            
            
        } catch (Exception e) {
        }
        return "FormReservaciones.xhtml?faces-redirect=true";
    }

    public String guardarReservacion() throws Exception {
        reservacion.setUsuario(usuario);
        reservacion.setVuelo(vuelo);
        reservacionDaoImpl.saveOrUpdate(reservacion);
        return "ListadoReservaciones.xhtml?faces-redirect=true";
    }

    public String editReservacion(Reservacion r) throws Exception {
        reservacion = r;
        usuario = reservacion.getUsuario();
        vuelo = reservacion.getVuelo();
        return "FormReservaciones.xhtml?faces-redirect=true";
    }

    public String deleteReservacion(Reservacion r) throws Exception {
        reservacionDaoImpl.delete(r.getIdreservacion());
        return "ListadoReservaciones.xhtml?faces-redirect=true";
    }    
    
    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public List<Vuelo> getVuelos() throws Exception {
        vuelos = vueloDaoImpl.listarVuelos(null,null,0,0);
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public List<Usuario> getUsuarios() throws Exception {
        usuarios = usuarioDaoImpl.listarUsuario();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Reservacion> getReservaciones() throws Exception {
        idvuelo = idvuelo == null ? 0 : idvuelo;
        reservaciones = reservacionDaoImpl.listarReservaciones(idusuario,idvuelo,fecha,nboletos,precio);
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public VueloDaoImpl getVueloDaoImpl() {
        return vueloDaoImpl;
    }

    public void setVueloDaoImpl(VueloDaoImpl vueloDaoImpl) {
        this.vueloDaoImpl = vueloDaoImpl;
    }

    public UsuarioDaoImpl getUsuarioDaoImpl() {
        return usuarioDaoImpl;
    }

    public void setUsuarioDaoImpl(UsuarioDaoImpl usuarioDaoImpl) {
        this.usuarioDaoImpl = usuarioDaoImpl;
    }

    public ReservacionDaoImpl getReservacionDaoImpl() {
        return reservacionDaoImpl;
    }

    public void setReservacionDaoImpl(ReservacionDaoImpl reservacionDaoImpl) {
        this.reservacionDaoImpl = reservacionDaoImpl;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdvuelo() {
        return idvuelo;
    }

    public void setIdvuelo(Integer idvuelo) {
        this.idvuelo = idvuelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Integer getNboletos() {
        return nboletos;
    }

    public void setNboletos(Integer nboletos) {
        this.nboletos = nboletos;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

  
}
