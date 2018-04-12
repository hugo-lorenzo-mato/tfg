package es.enxenio.GGAL1701.model.dominio.citas.citaLibro;

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
public class CitaLibroServiceImpl implements CitaLibroService {

    @Inject
    private CitaLibroRepository citaLibroRepository;

    // Métodos de consulta

    @Override
    @Transactional(readOnly = true)
    public CitaLibro get(Long id) {
        return citaLibroRepository.findOne(id);
    }

    @Override
    public Page<CitaLibro> filter(PageableFilter filter) {
        return citaLibroRepository.filter(filter.getQuery(), filter.getPageable());
    }

    // Métodos de gestión

    @Override
    public CitaLibro save(CitaLibro citaLibro) throws InstanceAlreadyExistsException {
        return citaLibroRepository.save(citaLibro);
    }

    @Override
    public CitaLibro update(CitaLibro citaLibro) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(citaLibro);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (citaLibroRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        citaLibroRepository.delete(id);
    }
}
