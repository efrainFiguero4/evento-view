package pe.com.eventoview.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.com.eventoview.persistence.entity.Usuario;
import pe.com.eventoview.service.LoginService;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<Usuario> loguearUsuario(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(loginService.loginUsuario(request.get("usuario"), request.get("password")));
    }
}
