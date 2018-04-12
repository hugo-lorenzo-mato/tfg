package es.enxenio.GGAL1701.model.dominio.lugar.ciudad;

import es.enxenio.GGAL1701.controller.custom.util.CiudadFilter;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by crodriguez on 11/05/2017.
 */
@Service
@Transactional
public class CiudadServiceImpl implements CiudadService {

    @Inject
    private CiudadRepository ciudadRepository;

    // Consulta

    @Override
    @Transactional(readOnly = true)
    public Ciudad get(Long id) {
        return ciudadRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ciudad> filter(PageableFilter filter) {
        return ciudadRepository.filter(filter.getQuery(), ((CiudadFilter) filter).getPais(), filter.getPageable());
    }

    // Gestión

    @Override
    public Ciudad save(Ciudad ciudad) throws InstanceAlreadyExistsException {
        return ciudadRepository.save(ciudad);
    }

    @Override
    public Ciudad update(Ciudad ciudad) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return ciudadRepository.save(ciudad);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (ciudadRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        ciudadRepository.delete(id);
    }
}
