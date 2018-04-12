package es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto;

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
public class SubidaPrototextoServiceImpl implements SubidaPrototextoService {

    @Inject
    private SubidaPrototextoRepository subidaPrototextoRepository;

    @Inject
    private ArchivoEntidadHelper archivoEntidadHelper;

    /*
    * Realizar consultas
    */

    @Override
    @Transactional(readOnly = true)
    public SubidaPrototexto get(Long id) {
        return subidaPrototextoRepository.findOne(id);
    }

    @Override
    public Page<SubidaPrototexto> filter(PageableFilter filter) {
        return subidaPrototextoRepository.filter((filter.getQuery()), filter.getPageable());
    }

    /*
    * Realizar gestión
    */

    @Override
    public SubidaPrototexto save(SubidaPrototexto subidaPrototexto, long id) throws InstanceAlreadyExistsException {
        saveOtro(subidaPrototexto, id);
        return subidaPrototextoRepository.save(subidaPrototexto);
    }

    @Override
    public SubidaPrototexto update(SubidaPrototexto subidaPrototexto, long id) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(subidaPrototexto, id);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (subidaPrototextoRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        }
        subidaPrototextoRepository.delete(id);
    }

    /*
     * documentos anexados: "otros". SubidaPrototexto múltiple
     */

    private void saveOtro(SubidaPrototexto subidaPrototexto, long idProto) {
        String path;
        if (subidaPrototexto.getId() != null) {
            SubidaPrototexto prototextoBD = get(subidaPrototexto.getId());
            boolean eliminar = prototextoBD.getOtroPath() != null && subidaPrototexto.getOtroPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, subidaPrototexto.getOtroTmp(), subidaPrototexto.getOtroPath(),
                prototextoBD.getOtroPath(), ConstantesModel.URL_CARPETA_PROTOTEXTOS_OTROS, idProto);
        } else {
            subidaPrototextoRepository.save(subidaPrototexto);
            path = archivoEntidadHelper.gestionarArchivo(false, subidaPrototexto.getOtroTmp(), subidaPrototexto.getOtroPath(),
                null, ConstantesModel.URL_CARPETA_PROTOTEXTOS_OTROS, idProto);
        }
        subidaPrototexto.setOtroPath(path);
    }
}
