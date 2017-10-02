package com.aerolinea.control;

import com.aerolinea.dao.AeropuertoDaoImpl;
import com.aerolinea.dao.AvionDaoImpl;
import com.aerolinea.dao.VueloDaoImpl;
import com.aerolinea.entidad.Avion;
import com.aerolinea.entidad.Aeropuerto;
import com.aerolinea.entidad.Vuelo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controlVuelos")
@SessionScoped
public class ControlVuelos implements Serializable {

    private Vuelo vuelo;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Avion avion;
    private List<Vuelo> vuelos;
    private List<Avion> aviones;
    private List<Aeropuerto> origenes;
    private List<Aeropuerto> destinos;
    @ManagedProperty("#{VueloDaoImpl}")
    private VueloDaoImpl vueloDaoImpl;
    @ManagedProperty("#{AeropuertoDaoImpl}")
    private AeropuertoDaoImpl aeropuertoDaoImpl;
    @ManagedProperty("#{AvionDaoImpl}")
    private AvionDaoImpl avionDaoImpl;

    //busqueda
    private Date fecha1;
    private Date fecha2;
    private Integer idorigen;
    private Integer iddestino;
    private Integer idavion;
    private Integer capacidad;


    
    @PostConstruct
    public void init() {
        try {
            vuelo = vueloDaoImpl.create();
            origen = aeropuertoDaoImpl.create();
            destino = aeropuertoDaoImpl.create();
            avion = avionDaoImpl.create();
        } catch (Exception e) {
        }
    }

    public ControlVuelos() {
    }

    public String preparaNuevo() {
        try {
            vuelo = vueloDaoImpl.create();
            origen = aeropuertoDaoImpl.create();
            destino = aeropuertoDaoImpl.create();
            avion = avionDaoImpl.create();
        } catch (Exception e) {
        }
        return "FormVuelos.xhtml?faces-redirect=true";
    }

    public String guardarVuelo() throws Exception {
        vuelo.setAeropuertoByIddestino(destino);
        vuelo.setAeropuertoByIdorigen(origen);
        vuelo.setAvion(avion);
        vueloDaoImpl.saveOrUpdate(vuelo);
        return "ListadoVuelos.xhtml?faces-redirect=true";
    }

    public String editVuelo(Vuelo v) throws Exception {
        vuelo = v;
        origen = vuelo.getAeropuertoByIdorigen();
        destino = vuelo.getAeropuertoByIddestino();
        avion = vuelo.getAvion();
        return "FormVuelos.xhtml?faces-redirect=true";
    }

    public String deleteVuelo(Vuelo v) throws Exception {
        vueloDaoImpl.delete(v.getIdvuelo());
        return "ListadoVuelos.xhtml?faces-redirect=true";
    }    
    
    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public Avion getAvion() throws Exception {
        if(avion == null) {
            avion = avionDaoImpl.create();
        }
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Vuelo> getVuelos() throws Exception {
        idorigen = idorigen == null ? 0 : idorigen;
        iddestino = iddestino == null ? 0 : iddestino;
        vuelos = vueloDaoImpl.listarVuelos(fecha1, fecha2, idorigen, iddestino);
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public List<Avion> getAviones() throws Exception {
        idavion = idavion == null ? 0 : idavion;
        capacidad = capacidad == null ? 0 : capacidad;
        aviones = avionDaoImpl.listarAviones(idavion, capacidad);
        return aviones;
    }

    public void setAviones(List<Avion> aviones) {
        this.aviones = aviones;
    }

    public List<Aeropuerto> getOrigenes() throws Exception {
        origenes = aeropuertoDaoImpl.findAll();
        return origenes;
    }

    public void setOrigenes(List<Aeropuerto> origenes) {
        this.origenes = origenes;
    }

    public List<Aeropuerto> getDestinos() throws Exception {
        destinos = aeropuertoDaoImpl.findAll();
        return destinos;
    }

    public void setDestinos(List<Aeropuerto> destinos) {
        this.destinos = destinos;
    }

    public VueloDaoImpl getVueloDaoImpl() {
        return vueloDaoImpl;
    }

    public void setVueloDaoImpl(VueloDaoImpl vueloDaoImpl) {
        this.vueloDaoImpl = vueloDaoImpl;
    }

    public AeropuertoDaoImpl getAeropuertoDaoImpl() {
        return aeropuertoDaoImpl;
    }

    public void setAeropuertoDaoImpl(AeropuertoDaoImpl aeropuertoDaoImpl) {
        this.aeropuertoDaoImpl = aeropuertoDaoImpl;
    }

    public AvionDaoImpl getAvionDaoImpl() {
        return avionDaoImpl;
    }

    public void setAvionDaoImpl(AvionDaoImpl avionDaoImpl) {
        this.avionDaoImpl = avionDaoImpl;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Integer getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(Integer idorigen) {
        this.idorigen = idorigen;
    }

    public Integer getIddestino() {
        return iddestino;
    }

    public void setIddestino(Integer iddestino) {
        this.iddestino = iddestino;
    }

    public Integer getIdavion() {
        return idavion;
    }

    public void setIdavion(Integer idavion) {
        this.idavion = idavion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }    
}
