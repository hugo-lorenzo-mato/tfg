package es.enxenio.GGAL1701.model.dominio.citas.citaPeriodico;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 01/09/2017.
 **/
@Service
@Transactional
public class CitaPeriodicoServiceImpl implements CitaPeriodicoService {

    @Inject
    private CitaPeriodicoRepository citaPeriodicoRepository;

    // Métodos de consulta

    @Override
    @Transactional(readOnly = true)
    public CitaPeriodico get(Long id) {
        return citaPeriodicoRepository.findOne(id);
    }

    @Override
    public Page<CitaPeriodico> filter(PageableFilter filter) {
        return citaPeriodicoRepository.filter(filter.getQuery(), filter.getPageable());
    }

    // Métodos de gestión

    @Override
    public CitaPeriodico save(CitaPeriodico citaPeriodico) throws InstanceAlreadyExistsException {
        return citaPeriodicoRepository.save(citaPeriodico);
    }

    @Override
    public CitaPeriodico update(CitaPeriodico citaPeriodico) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(citaPeriodico);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (citaPeriodicoRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        citaPeriodicoRepository.delete(id);
    }
}
