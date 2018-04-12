package es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionAgente;
import es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoAgente;
import es.enxenio.GGAL1701.model.util.tesauro.Tesauro;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 05/09/2017.
 */
@Entity
@Table(name = "funcion_agente")
public class FuncionAgente extends Tesauro {

    // El campo nombre es para Español

    @Column(name = "nombre_en")
    private String nombreEn;

    @Column(name = "nombre_gl")
    private String nombreGl;


    public FuncionAgente() {}

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
