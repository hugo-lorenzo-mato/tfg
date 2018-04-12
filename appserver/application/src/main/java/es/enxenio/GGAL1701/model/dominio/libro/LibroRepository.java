package es.enxenio.GGAL1701.model.dominio.libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 04/08/2017.
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT l" +
        " FROM Libro l" +
        " WHERE ( str(l.id) like %:query%" +
        " OR lower(unaccent(l.titulo)) like %:query% )" +
        " AND (:revisado is null OR l.revisado = :revisado)" +
        " AND (:concluido is null OR l.concluido = :concluido)" +
        " AND (:tipo is null OR l.tipo = :tipo)")
    Page<Libro> filter(@Param("query") String query, @Param("tipo") TipoLibro tipo, @Param("revisado") Boolean revisado, @Param("concluido") Boolean concluido, Pageable pageable);

}
