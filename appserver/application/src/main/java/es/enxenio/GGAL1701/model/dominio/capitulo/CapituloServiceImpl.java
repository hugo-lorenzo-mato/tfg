package es.enxenio.GGAL1701.model.dominio.capitulo;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
/**
 * Created by hlorenzo on 08/09/2017.
 **/
@Service
@Transactional
public class CapituloServiceImpl implements CapituloService {

    @Inject
    private CapituloRepository capituloRepository;

    @Override
    @Transactional(readOnly = true)
    public Capitulo get(Long id) {
        return capituloRepository.findOne(id);
    }

    @Override
    public Page<Capitulo> filter(PageableFilter filter) {
        return capituloRepository.filter(filter.getQuery(), filter.getPageable());
    }

    @Override
    public Capitulo save(Capitulo capitulo) throws InstanceAlreadyExistsException {
        return capituloRepository.save(capitulo);
    }

    @Override
    public Capitulo update(Capitulo capitulo) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(capitulo);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if(capituloRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        }
        capituloRepository.delete(id);
    }
}
