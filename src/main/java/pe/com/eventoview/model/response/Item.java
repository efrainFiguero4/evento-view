package pe.com.eventoview.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item<T, D> {
    private T codigo;
    private D valor;
}
