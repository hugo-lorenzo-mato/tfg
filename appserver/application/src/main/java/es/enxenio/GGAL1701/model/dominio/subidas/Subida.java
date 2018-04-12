package es.enxenio.GGAL1701.model.dominio.subidas;


import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class Subida extends GenericEntity{

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "otro_path")
    private String otroPath;

    /*
    * La identifico como transient para recalcar su carácter temporal.
    * De serializar el objeto, su valor no se serializará. No lo alma-
    * cenamos en la BD por tanto tampoco.
    */
    @Transient
    private String otroTmp;

    public String getOtroPath() {
        return otroPath;
    }

    public void setOtroPath(String otroPath) {
        this.otroPath = otroPath;
    }

    public String getOtroTmp() {
        return otroTmp;
    }

    public void setOtroTmp(String otroTmp) {
        this.otroTmp = otroTmp;
    }
}
