package es.enxenio.GGAL1701.model.dominio.lugar.ciudad;

import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by crodriguez on 11/05/2017.
 */
@Entity
@Table(name = "ciudad")
public class Ciudad extends GenericEntity {

    // El campo nombre es para Español
    @NotNull
    private String nombre;

    @Column(name = "nombre_en")
    private String nombreEn;

    @Column(name = "nombre_gl")
    private String nombreGl;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;


    public Ciudad() {}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEn() {
        return nombreEn;
    }

    public void setNombreEn(String nombreEn) {
        this.nombreEn = nombreEn;
    }

    public String getNombreGl() {
        return nombreGl;
    }

    public void setNombreGl(String nombreGl) {
        this.nombreGl = nombreGl;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

}
