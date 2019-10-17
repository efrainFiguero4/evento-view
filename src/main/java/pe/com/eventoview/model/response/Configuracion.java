package pe.com.eventoview.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Configuracion<C, T, D, E> {
    private C configuracion;
    private T evento;
    private D funcion;
    private E zona;
}
