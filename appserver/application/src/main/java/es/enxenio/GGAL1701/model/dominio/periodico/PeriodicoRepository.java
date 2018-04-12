package es.enxenio.GGAL1701.model.dominio.periodico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 04/08/2017.
 */
public interface PeriodicoRepository extends JpaRepository<Periodico, Long > {

    @Query(value = "SELECT p" +
        " FROM Periodico p" +
        " WHERE ( str(p.id) like %:query%" +
        " OR lower(unaccent(p.titulo)) like %:query% )" +
        " AND (:revisado is null OR p.revisado = :revisado)" +
        " AND (:concluido is null OR p.concluido = :concluido)" +
        " AND (:tipo is null OR p.tipo = :tipo)")
    Page<Periodico> filter(@Param("query") String query, @Param("tipo") TipoPeriodico tipo, @Param("revisado") Boolean revisado, @Param("concluido") Boolean concluido, Pageable pageable);

}
