package pe.com.eventoview.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.eventoview.persistence.entity.Codigo;

import java.util.List;
import java.util.Optional;

public interface CodigoRepository extends JpaRepository<Codigo, Integer> {

    public Optional<Codigo> findByNumero(String numero);

    public List<Codigo> findByIdEventoAndIdFuncionAndIdZonaAndNumeroAndConfirmadoIsFalse(Integer idEvento, Integer idFuncion, Integer idZona, String numero);
}
