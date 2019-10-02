package pe.com.eventoview.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pe.com.eventoview.persistence.entity.Usuario;
import pe.com.eventoview.persistence.entity.Zona;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> , JpaSpecificationExecutor<Usuario> {
     public Usuario findByUsuarioAndPasswordAndEstadoIsTrue(String usuario, String password);
}
