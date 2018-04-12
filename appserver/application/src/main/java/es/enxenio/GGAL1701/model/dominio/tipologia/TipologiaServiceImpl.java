package es.enxenio.GGAL1701.model.dominio.tipologia;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 05/09/2017.
 */
@Service
@Transactional
public class TipologiaServiceImpl implements TipologiaService {

    @Inject
    private TipologiaRepository tipologiaRepository;

    // Consulta

    @Override
    @Transactional(readOnly = true)
    public Tipologia get(Long id) {
        return tipologiaRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tipologia> filter(PageableFilter filter) {
        return tipologiaRepository.filterByNombre(filter.getQuery(), filter.getPageable());
    }

    // Gestión

    @Override
    public Tipologia save(Tipologia tipologia) throws InstanceAlreadyExistsException {
        return tipologiaRepository.save(tipologia);
    }

    @Override
    public Tipologia update(Tipologia tipologia) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        // unused
        return tipologiaRepository.save(tipologia);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (tipologiaRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        tipologiaRepository.delete(id);
    }

}
