package com.aerolinea.entidad;
// Generated 17-sep-2017 10:17:57 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Aeropuerto generated by hbm2java
 */
@Entity
@Table(name="aeropuerto"
    ,catalog="aerolinea"
)
public class Aeropuerto  implements java.io.Serializable {

     @Min(value=1, message="{Aeropuerto.idaeropuerto.min}")
     private Integer idaeropuerto;
     private Pais pais;
     @NotEmpty(message="{Aeropuerto.aeropuerto.notempty}")
     private String aeropuerto;
     private String ciudad;
     private Set<Vuelo> vuelosForIdorigen = new HashSet<Vuelo>(0);
     private Set<Vuelo> vuelosForIddestino = new HashSet<Vuelo>(0);

    public Aeropuerto() {
    }

	
    public Aeropuerto(Pais pais, String aeropuerto, String ciudad) {
        this.pais = pais;
        this.aeropuerto = aeropuerto;
        this.ciudad = ciudad;
    }
    public Aeropuerto(Pais pais, String aeropuerto, String ciudad, Set<Vuelo> vuelosForIdorigen, Set<Vuelo> vuelosForIddestino) {
       this.pais = pais;
       this.aeropuerto = aeropuerto;
       this.ciudad = ciudad;
       this.vuelosForIdorigen = vuelosForIdorigen;
       this.vuelosForIddestino = vuelosForIddestino;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idaeropuerto", unique=true, nullable=false)
    public Integer getIdaeropuerto() {
        return this.idaeropuerto;
    }
    
    public void setIdaeropuerto(Integer idaeropuerto) {
        this.idaeropuerto = idaeropuerto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpais", nullable=false)
    public Pais getPais() {
        return this.pais;
    }
    
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
    @Column(name="aeropuerto", nullable=false, length=60)
    public String getAeropuerto() {
        return this.aeropuerto;
    }
    
    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    
    @Column(name="ciudad", nullable=false, length=45)
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="aeropuertoByIdorigen")
    public Set<Vuelo> getVuelosForIdorigen() {
        return this.vuelosForIdorigen;
    }
    
    public void setVuelosForIdorigen(Set<Vuelo> vuelosForIdorigen) {
        this.vuelosForIdorigen = vuelosForIdorigen;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="aeropuertoByIddestino")
    public Set<Vuelo> getVuelosForIddestino() {
        return this.vuelosForIddestino;
    }
    
    public void setVuelosForIddestino(Set<Vuelo> vuelosForIddestino) {
        this.vuelosForIddestino = vuelosForIddestino;
    }




}


