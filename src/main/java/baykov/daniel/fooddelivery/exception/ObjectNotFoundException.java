package baykov.daniel.fooddelivery.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private final Long id;
    private final String objectType;

    public ObjectNotFoundException(Long id, String objectType) {
        this.id = id;
        this.objectType = objectType;
    }
}
