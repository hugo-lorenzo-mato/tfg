package es.enxenio.GGAL1701.model.dominio.lugar.pais;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by crodriguez on 06/06/2017.
 */
@Service
@Transactional
public class PaisServiceImpl implements PaisService {

    @Inject
    private PaisRepository paisRepository;

    // Consulta

    @Override
    @Transactional(readOnly = true)
    public Pais get(Long id) {
        return paisRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pais> filter(PageableFilter filter) {
        return paisRepository.filterByNombre(filter.getQuery(), filter.getPageable());
    }

    // Gestión

    @Override
    public Pais save(Pais pais) throws InstanceAlreadyExistsException {
        return paisRepository.save(pais);
    }

    @Override
    public Pais update(Pais pais) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        // unused
        return paisRepository.save(pais);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (paisRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        paisRepository.delete(id);
    }

}
