package baykov.daniel.fooddelivery.web;

import baykov.daniel.fooddelivery.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import static baykov.daniel.fooddelivery.constant.ControllerConstants.OBJECT_ID;
import static baykov.daniel.fooddelivery.constant.ControllerConstants.OBJECT_TYPE;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView productNotFound(ObjectNotFoundException objectNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject(OBJECT_ID, objectNotFoundException.getId());
        modelAndView.addObject(OBJECT_TYPE, objectNotFoundException.getObjectType());
        return modelAndView;
    }
}
