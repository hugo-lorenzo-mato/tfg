package es.enxenio.GGAL1701.model.dominio.prototexto;


import com.sun.javafx.binding.StringFormatter;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.custom.util.PrototextoFilter;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototextoService;
import es.enxenio.GGAL1701.model.util.Imagen;
import es.enxenio.GGAL1701.util.ConstantesModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.enxenio.GGAL1701.util.upload.ArchivoEntidadHelper;
import es.enxenio.GGAL1701.util.upload.ImagenUtil;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
/**
 * Created by hlorenzo on 06/08/2017.
 */
@Service
@Transactional
public class PrototextoServiceImpl implements PrototextoService{

    @Inject
    private PrototextoRepository prototextoRepository;

    @Inject
    private SubidaPrototextoService subidaPrototextoService;

    @Inject
    private ArchivoEntidadHelper archivoEntidadHelper;


    /*
    * Realizar consultas
    */

    @Override
    @Transactional(readOnly = true)
    public Prototexto get(Long id) {
        return prototextoRepository.findOne(id);
    }

    @Override
    public Page<Prototexto> filter(PageableFilter filter) {
        return prototextoRepository.filter(filter.getQuery(), ((PrototextoFilter) filter).getTipo(), ((PrototextoFilter) filter).getRevisado(), ((PrototextoFilter) filter).getConcluido(), filter.getPageable());
    }

    /* Realizar gestión */

    @Override
    public Prototexto save(Prototexto prototexto) throws InstanceAlreadyExistsException {
        saveCompleto(prototexto);
        saveTranscripcion(prototexto);
        saveGenetica(prototexto);
        saveImagen(prototexto.getImagen(), prototexto);
        numeracionAutomatica(prototexto);
        for (SubidaPrototexto subida: prototexto.getSubidaPrototextos()
            ) {
            subidaPrototextoService.save(subida,prototexto.getId());
        }
        return prototextoRepository.save(prototexto);
    }

    @Override
    public Prototexto update(Prototexto prototexto) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(prototexto);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (prototextoRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        prototextoRepository.delete(id);
    }

    /*
     * Imagenes y documentos anexados
     */

    private void saveCompleto(Prototexto prototexto) {
        String path;
        if (prototexto.getId() != null) {
            Prototexto prototextoBD = get(prototexto.getId());
            boolean eliminar = prototextoBD.getCompletoPath() != null && prototexto.getCompletoPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, prototexto.getCompletoTmp(), prototexto.getCompletoPath(),
                prototextoBD.getCompletoPath(), ConstantesModel.URL_CARPETA_PROTOTEXTOS_COMPLETOS, prototexto.getId());
        } else {
            prototextoRepository.save(prototexto);
            path = archivoEntidadHelper.gestionarArchivo(false, prototexto.getCompletoTmp(), prototexto.getCompletoPath(),
                null, ConstantesModel.URL_CARPETA_PROTOTEXTOS_COMPLETOS, prototexto.getId());
        }
        prototexto.setCompletoPath(path);
    }


    private void saveTranscripcion(Prototexto prototexto) {
        String path;
        if (prototexto.getId() != null) {
            Prototexto prototextoBD = get(prototexto.getId());
            boolean eliminar = prototextoBD.getTranscripcionPath() != null && prototexto.getTranscripcionPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, prototexto.getTranscripcionTmp(), prototexto.getTranscripcionPath(),
                prototextoBD.getTranscripcionPath(), ConstantesModel.URL_CARPETA_PROTOTEXTOS_TRANSCRIPCION, prototexto.getId());
        } else {
            prototextoRepository.save(prototexto);
            path = archivoEntidadHelper.gestionarArchivo(false, prototexto.getTranscripcionTmp(), prototexto.getTranscripcionPath(),
                null, ConstantesModel.URL_CARPETA_PROTOTEXTOS_TRANSCRIPCION, prototexto.getId());
        }
        prototexto.setTranscripcionPath(path);
    }



    private void saveGenetica(Prototexto prototexto) {
        String path;
        if (prototexto.getId() != null) {
            Prototexto prototextoBD = get(prototexto.getId());
            boolean eliminar = prototextoBD.getGeneticaPath() != null && prototexto.getGeneticaPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, prototexto.getGeneticaTmp(), prototexto.getGeneticaPath(),
                prototextoBD.getGeneticaPath(), ConstantesModel.URL_CARPETA_PROTOTEXTOS_CRITICA, prototexto.getId());
        } else {
            prototextoRepository.save(prototexto);
            path = archivoEntidadHelper.gestionarArchivo(false, prototexto.getGeneticaTmp(), prototexto.getGeneticaPath(),
                null, ConstantesModel.URL_CARPETA_PROTOTEXTOS_CRITICA, prototexto.getId());
        }
        prototexto.setGeneticaPath(path);
    }

    private void saveImagen(Imagen imagen, Prototexto prototexto) {
        if (imagen == null) {
            imagen = new Imagen();
        }
        String path = archivoEntidadHelper.gestionarArchivo(imagen.getEliminar(), imagen.getArchivoTemporal(),
            imagen.getPath(), prototexto.getImagenPath(), ConstantesModel.URL_CARPETA_PROTOTEXTOS_IMAGEN, prototexto.getId());

        if (path != null) {
            archivoEntidadHelper.guardarThumbnailsImagen(ImagenUtil.getThumbnailsPortada(),
                StringFormatter.format(ConstantesModel.URL_CARPETA_PROTOTEXTOS_IMAGEN, prototexto.getId()).getValue() + "/"
                    + path,
                StringFormatter.format(ConstantesModel.URL_CARPETA_PROTOTEXTOS_IMAGEN, prototexto.getId()).getValue());
        }
        prototexto.setImagenPath(path);
    }

    private void numeracionAutomatica(Prototexto p){
        Long idNum = p.getId();
        String idText = String.format("%03d", idNum);
        String cat;
        if (p.getTipo() != null) {
            cat = p.getTipo().toString();
        } else{
            cat = "---";
        }
        String ano;
        if (p.getPublicacionAno() != null) {
            if (p.getPublicacionAno().toString().length() > 2) {
                ano = p.getPublicacionAno().toString().substring(2);
            } else {
                ano = p.getPublicacionAno().toString();
            }
            // El 01 es porque es un identificador de clase. En este caso, los prototextos serán 01
            p.setNumeracionAutomatica("01" + cat + idText + ano);
        } else{
            p.setNumeracionAutomatica("01" + cat + idText);
        }
    }

}
