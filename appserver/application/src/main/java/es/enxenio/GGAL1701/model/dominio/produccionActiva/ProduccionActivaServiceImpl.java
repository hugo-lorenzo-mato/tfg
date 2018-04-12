package es.enxenio.GGAL1701.model.dominio.produccionActiva;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.publica.custom.BusquedaAvanzadaFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;


/**
 * Created by hlorenzo on 26/09/2017.
 */
@Service
@Transactional
public class ProduccionActivaServiceImpl implements ProduccionActivaService {

    @Inject
    private ProduccionActivaRepository produccionActivaRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<ProduccionActiva> filter(PageableFilter filter) {
        return produccionActivaRepository.filter((BusquedaAvanzadaFilter) filter);
    }


}

