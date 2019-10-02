package pe.com.eventoview.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.eventoview.persistence.entity.Codigo;
import pe.com.eventoview.persistence.entity.Funcion;

public interface FuncionRepository extends JpaRepository<Funcion, Integer> {
}
