package es.enxenio.GGAL1701.model.dominio.prototexto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 03/08/2017.
 */
public interface PrototextoRepository extends JpaRepository<Prototexto, Long> {

    @Query(value = "SELECT p" +
        " FROM Prototexto p" +
        " WHERE ( str(p.id) like %:query%" +
        " OR lower(unaccent(p.titulo)) like %:query% )" +
        " AND (:revisado is null OR p.revisado = :revisado)" +
        " AND (:concluido is null OR p.concluido = :concluido)" +
        " AND (:tipo is null OR p.tipo = :tipo)")
    Page<Prototexto> filter(@Param("query") String query, @Param("tipo") TipoPrototexto tipo, @Param("revisado") Boolean revisado, @Param("concluido") Boolean concluido, Pageable pageable);
}
