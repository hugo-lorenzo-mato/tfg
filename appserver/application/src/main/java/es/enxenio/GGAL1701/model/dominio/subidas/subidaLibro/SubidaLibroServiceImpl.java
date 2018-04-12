package es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.util.ConstantesModel;
import es.enxenio.GGAL1701.util.upload.ArchivoEntidadHelper;
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
public class SubidaLibroServiceImpl implements SubidaLibroService {

    @Inject
    private SubidaLibroRepository subidaLibroRepository;

    @Inject
    private ArchivoEntidadHelper archivoEntidadHelper;

    /*
    * Realizar consultas
    */

    @Override
    @Transactional(readOnly = true)
    public SubidaLibro get(Long id) {
        return subidaLibroRepository.findOne(id);
    }

    @Override
    public Page<SubidaLibro> filter(PageableFilter filter) {
        return subidaLibroRepository.filter((filter.getQuery()), filter.getPageable());
    }

    /*
    * Realizar gestión
    */

    @Override
    public SubidaLibro save(SubidaLibro subidaLibro, long id) throws InstanceAlreadyExistsException {
        saveOtro(subidaLibro, id);
        return subidaLibroRepository.save(subidaLibro);
    }

    @Override
    public SubidaLibro update(SubidaLibro subidaLibro, long id) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(subidaLibro, id);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (subidaLibroRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        }
        subidaLibroRepository.delete(id);
    }

    /*
     * documentos anexados: "otros". SubidaLibro múltiple
     */

    private void saveOtro(SubidaLibro subidaLibro, long idLibro) {
        String path;
        if (subidaLibro.getId() != null) {
            SubidaLibro subidaLibroBD = get(subidaLibro.getId());
            boolean eliminar = subidaLibroBD.getOtroPath() != null && subidaLibro.getOtroPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, subidaLibro.getOtroTmp(), subidaLibro.getOtroPath(),
                subidaLibroBD.getOtroPath(), ConstantesModel.URL_CARPETA_LIBROS_OTROS, idLibro);
        } else {
            subidaLibroRepository.save(subidaLibro);
            path = archivoEntidadHelper.gestionarArchivo(false, subidaLibro.getOtroTmp(), subidaLibro.getOtroPath(),
                null, ConstantesModel.URL_CARPETA_LIBROS_OTROS, idLibro);
        }
        subidaLibro.setOtroPath(path);
    }
}
