package com.MainController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandleException {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleError405(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String header  = request.getHeader("ajax");
        String response = "";
        if(header == null){
            view.setViewName("login");
        }else {
            response = "error 405";
        }
        System.out.println("405");
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(header == null?view:response);
    }


}
