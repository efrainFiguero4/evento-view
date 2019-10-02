package pe.com.eventoview.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pe.com.eventoview.persistence.entity.Usuario;
import pe.com.eventoview.persistence.repositories.UsuarioRepository;

@Log4j2
@Service
@AllArgsConstructor
public class LoginService {

    private UsuarioRepository usuarioRepository;

    public Usuario loginUsuario(String username, String password) {
        return usuarioRepository.findByUsuarioAndPasswordAndEstadoIsTrue(username, password);
    }
}
