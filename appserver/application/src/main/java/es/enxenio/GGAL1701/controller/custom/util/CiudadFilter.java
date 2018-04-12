package es.enxenio.GGAL1701.controller.custom.util;

import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;


/**
 * Created by hlorenzo on 24/09/2017.
 */
public class CiudadFilter extends PageableFilter{

    private Pais pais;

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
