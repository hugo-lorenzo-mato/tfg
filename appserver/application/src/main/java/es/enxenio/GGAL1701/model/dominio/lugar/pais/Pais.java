package es.enxenio.GGAL1701.model.dominio.lugar.pais;

import es.enxenio.GGAL1701.model.util.tesauro.Tesauro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by crodriguez on 06/06/2017.
 */
@Entity
@Table(name = "pais")
public class Pais extends Tesauro {

    // El campo nombre es para Español

    @Column(name = "nombre_en")
    private String nombreEn;

    @Column(name = "nombre_gl")
    private String nombreGl;

    private Pais() {}

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
}
