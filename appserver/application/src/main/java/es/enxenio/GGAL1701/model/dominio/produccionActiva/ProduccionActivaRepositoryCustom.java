package es.enxenio.GGAL1701.model.dominio.produccionActiva;

import es.enxenio.GGAL1701.controller.publica.custom.BusquedaAvanzadaFilter;
import org.springframework.data.domain.Page;

/**
 * Created by hlorenzo on 25/09/2017.
 */
public interface ProduccionActivaRepositoryCustom {

    Page<ProduccionActiva> filter(BusquedaAvanzadaFilter filter);

}
