package es.enxenio.GGAL1701.model.dominio.libro;

import com.sun.javafx.binding.StringFormatter;
import es.enxenio.GGAL1701.controller.custom.util.LibroFilter;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibro;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibroService;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototextoService;
import es.enxenio.GGAL1701.model.util.Imagen;
import es.enxenio.GGAL1701.util.ConstantesModel;
import es.enxenio.GGAL1701.util.upload.ArchivoEntidadHelper;
import es.enxenio.GGAL1701.util.upload.ImagenUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
/**
 * Created by hlorenzo on 04/08/2017.
 */
@Service
@Transactional
public class LibroServiceImpl implements LibroService {

    @Inject
    private LibroRepository libroRepository;

    @Inject
    private SubidaLibroService subidaLibroService;

    @Inject
    private ArchivoEntidadHelper archivoEntidadHelper;

    @Override
    @Transactional(readOnly = true)
    public Libro get(Long id) {
        return libroRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> filter(PageableFilter filter) {
        return libroRepository.filter(filter.getQuery(), ((LibroFilter) filter).getTipo(), ((LibroFilter) filter).getRevisado(), ((LibroFilter) filter).getConcluido(),  filter.getPageable());
    }

    /* Realizar gestión */

    @Override
    public Libro save(Libro libro) throws InstanceAlreadyExistsException {
        saveCompleto(libro);
        saveTranscripcion(libro);
        saveGenetica(libro);
        saveImagen(libro.getImagen(), libro);
        numeracionAutomatica(libro);
        numeracionEdicionesYCap(libro);
        for (SubidaLibro subida: libro.getSubidaLibros()
            ) {
            subidaLibroService.save(subida,libro.getId());
        }
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(Libro libro) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(libro);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (libroRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        } else{
            libroRepository.delete(id);
        }
    }

    /*
     * Imagenes y documentos anexados
     */

    // TODO: Seguramente se puede refactorizar y simplificar la manera en la que se guardan imágenes y archivos

    private void saveCompleto(Libro libro) {
        String path;
        if (libro.getId() != null) {
            Libro libroBD = get(libro.getId());
            boolean eliminar = libroBD.getCompletoPath() != null && libro.getCompletoPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, libro.getCompletoTmp(), libro.getCompletoPath(),
                libroBD.getCompletoPath(), ConstantesModel.URL_CARPETA_LIBROS_COMPLETOS, libro.getId());
        } else {
            libroRepository.save(libro);
            path = archivoEntidadHelper.gestionarArchivo(false, libro.getCompletoTmp(), libro.getCompletoPath(),
                null, ConstantesModel.URL_CARPETA_LIBROS_COMPLETOS, libro.getId());
        }
        libro.setCompletoPath(path);
    }


    private void saveTranscripcion(Libro libro) {
        String path;
        if (libro.getId() != null) {
            Libro libroBD = get(libro.getId());
            boolean eliminar = libroBD.getTranscripcionPath() != null && libro.getTranscripcionPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, libro.getTranscripcionTmp(), libro.getTranscripcionPath(),
                libroBD.getTranscripcionPath(), ConstantesModel.URL_CARPETA_LIBROS_TRANSCRIPCION, libro.getId());
        } else {
            libroRepository.save(libro);
            path = archivoEntidadHelper.gestionarArchivo(false, libro.getTranscripcionTmp(), libro.getTranscripcionPath(),
                null, ConstantesModel.URL_CARPETA_LIBROS_TRANSCRIPCION, libro.getId());
        }
        libro.setTranscripcionPath(path);
    }



    private void saveGenetica(Libro libro) {
        String path;
        if (libro.getId() != null) {
            Libro libroBD = get(libro.getId());
            boolean eliminar = libroBD.getGeneticaPath() != null && libro.getGeneticaPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, libro.getGeneticaTmp(), libro.getGeneticaPath(),
                libroBD.getGeneticaPath(), ConstantesModel.URL_CARPETA_LIBROS_CRITICA, libro.getId());
        } else {
            libroRepository.save(libro);
            path = archivoEntidadHelper.gestionarArchivo(false, libro.getGeneticaTmp(), libro.getGeneticaPath(),
                null, ConstantesModel.URL_CARPETA_LIBROS_CRITICA, libro.getId());
        }
        libro.setGeneticaPath(path);
    }

    private void saveImagen(Imagen imagen, Libro libro) {
        if (imagen == null) {
            imagen = new Imagen();
        }
        String path = archivoEntidadHelper.gestionarArchivo(imagen.getEliminar(), imagen.getArchivoTemporal(),
            imagen.getPath(), libro.getImagenPath(), ConstantesModel.URL_CARPETA_LIBROS_IMAGEN, libro.getId());

        if (path != null) {
            archivoEntidadHelper.guardarThumbnailsImagen(ImagenUtil.getThumbnailsPortada(),
                StringFormatter.format(ConstantesModel.URL_CARPETA_LIBROS_IMAGEN, libro.getId()).getValue() + "/"
                    + path,
                StringFormatter.format(ConstantesModel.URL_CARPETA_LIBROS_IMAGEN, libro.getId()).getValue());
        }
        libro.setImagenPath(path);
    }

    private void numeracionAutomatica(Libro p){
        Long idNum = p.getId();
        String idText = String.format("%03d", idNum);
        // Tenemos que omitir la categoría porque hemos decidido prescindir de ella en la reunión hasta recibir feedback
        //String cat = p.getTipo().toString().substring(0,3);
        String ano;
        if (p.getPublicacionAno() != null) {
            if (p.getPublicacionAno().toString().length() > 2) {
                ano = p.getPublicacionAno().toString().substring(2);
            } else {
                ano = p.getPublicacionAno().toString();
            }
            // El 02 es porque es un identificador de clase. En este caso, los prototextos serán 01, libro 02
            p.setNumeracionAutomatica("02" + "LIV" + idText + ano);
        } else{
            p.setNumeracionAutomatica("02" + "LIV" + idText);
        }
    }

    private void numeracionEdicionesYCap(Libro l){
        l.setNumeroEdiciones(l.getEdiciones().size());
        l.setNumeroCapitulos(l.getCapitulos().size());
    }
}
