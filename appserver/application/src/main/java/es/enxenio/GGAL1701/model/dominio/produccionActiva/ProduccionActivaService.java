package es.enxenio.GGAL1701.model.dominio.produccionActiva;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.generic.GenericService;
import org.springframework.data.domain.Page;

/**
 * Created by hlorenzo on 04/08/2017.
 */
public interface ProduccionActivaService {

    Page<ProduccionActiva> filter(PageableFilter filter);
}
