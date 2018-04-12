package es.enxenio.GGAL1701.model.dominio.citas.citaPrototexto;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 29/08/2017.
 **/
@Service
@Transactional
public class CitaPrototextoServiceImpl implements CitaPrototextoService {

    @Inject
    private CitaPrototextoRepository citaPrototextoRepository;

    // Métodos de consulta

    @Override
    @Transactional(readOnly = true)
    public CitaPrototexto get(Long id) {
        return citaPrototextoRepository.findOne(id);
    }

    @Override
    public Page<CitaPrototexto> filter(PageableFilter filter) {
        return citaPrototextoRepository.filter(filter.getQuery(), filter.getPageable());
    }

    // Métodos de gestión

    @Override
    public CitaPrototexto save(CitaPrototexto citaPrototexto) throws InstanceAlreadyExistsException {
        return citaPrototextoRepository.save(citaPrototexto);
    }

    @Override
    public CitaPrototexto update(CitaPrototexto citaPrototexto) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(citaPrototexto);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (citaPrototextoRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        citaPrototextoRepository.delete(id);
    }
}
