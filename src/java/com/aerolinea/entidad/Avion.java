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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Avion generated by hbm2java
 */
@Entity
@Table(name="avion"
    ,catalog="aerolinea"
)
public class Avion  implements java.io.Serializable {

     @Min(value=1, message="Seleccione avion")
     private Integer idavion;
     private int capacidad;
     @NotEmpty
     private String descripcion;
     private Set<Vuelo> vuelos = new HashSet<Vuelo>(0);

    public Avion() {
    }

	
    public Avion(int capacidad, String descripcion) {
        this.capacidad = capacidad;
        this.descripcion = descripcion;
    }
    public Avion(int capacidad, String descripcion, Set<Vuelo> vuelos) {
       this.capacidad = capacidad;
       this.descripcion = descripcion;
       this.vuelos = vuelos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idavion", unique=true, nullable=false)
    public Integer getIdavion() {
        return this.idavion;
    }
    
    public void setIdavion(Integer idavion) {
        this.idavion = idavion;
    }

    
    @Column(name="capacidad", nullable=false)
    public int getCapacidad() {
        return this.capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    
    @Column(name="descripcion", nullable=false, length=70)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="avion")
    public Set<Vuelo> getVuelos() {
        return this.vuelos;
    }
    
    public void setVuelos(Set<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }




}


