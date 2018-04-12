package es.enxenio.GGAL1701.model.dominio.tipologia;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionAgente;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionOrganizacion;
import es.enxenio.GGAL1701.model.util.tesauro.Tesauro;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 05/09/2017.
 */
@Entity
@Table(name = "tipologia")
public class Tipologia extends Tesauro {

    // El campo nombre es para Español

    @Column(name = "nombre_en")
    private String nombreEn;

    @Column(name = "nombre_gl")
    private String nombreGl;

    public Tipologia() {}

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
