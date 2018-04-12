package es.enxenio.GGAL1701.model.dominio.agente;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
/**
 * Created by hlorenzo on 04/09/2017.
 */
@Service
@Transactional
public class AgenteServiceImpl implements AgenteService {

    @Inject
    private AgenteRepository agenteRepository;

    // Consulta

    @Override
    @Transactional(readOnly = true)
    public Agente get(Long id) {
        return agenteRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Agente> filter(PageableFilter filter) {
        return agenteRepository.filter(filter.getQuery(), filter.getPageable());
    }

    // Gestión

    @Override
    public Agente save(Agente agente) throws InstanceAlreadyExistsException {
        return agenteRepository.save(agente);
    }

    @Override
    public Agente update(Agente agente) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(agente);
    }


    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (agenteRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        agenteRepository.delete(id);
    }

}
