package controller.validation;

import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.*;

@ControllerAdvice
public class DefaultExceptionHandler {

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler({MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            ServletRequestBindingException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleRequestException(Exception ex) {
        HashMap map = new HashMap();
        map.put("error", resolveMessage("RequestError"));
        map.put("cause", ex.getMessage());
        return map;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleValidationException(ConstraintViolationException ex) throws IOException {
        HashMap map = new HashMap();
        map.put("error", resolveMessage("ValidationFailure"));
        map.put("violations", convertConstraintViolation(ex.getConstraintViolations()));
        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) throws IOException {
        HashMap map = new HashMap();
        map.put("error", resolveMessage("ValidationFailure"));
        map.put("violations", convertConstraintViolation(ex.getBindingResult()));
        return map;
    }
    @ExceptionHandler(RepositoryConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleValidationException(RepositoryConstraintViolationException ex) throws IOException {
        Map<String, Object> map = new HashMap();
        map.put("error", resolveMessage("ValidationFailure"));
        map.put("violations", convertConstraintViolation(ex.getErrors()));
        return map;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public
    @ResponseBody
    Map<String, Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) throws IOException {
        Map<String, Object> map = new HashMap();
        map.put("error", resolveMessage("DataIntegrityError"));
        map.put("cause", ex.getCause().getCause().getLocalizedMessage());
        return map;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Map<String, Object> handleDataAccessException(DataAccessException ex) throws IOException {
        Map<String, Object> map = new HashMap();
        map.put("error", resolveMessage("DataError"));
        map.put("cause", ex.getCause().getMessage());
        return map;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public
    @ResponseBody
    Map<String, Object> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) throws IOException {
        Map<String, Object> map = new HashMap();
        map.put("error", resolveMessage("UnsupportedMediaType"));
        map.put("cause", ex.getLocalizedMessage());
        map.put("supported", ex.getSupportedMediaTypes());
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Map<String, Object> handleUncaughtException(Exception ex) throws IOException {
        Map<String, Object> map = new HashMap();
        map.put("error", resolveMessage("UnknownError"));
        if (ex.getCause() != null) {
            map.put("cause", ex.getCause().getMessage());
        } else {
            map.put("cause", ex.getMessage());
        }
        return map;
    }

    /*@ExceptionHandler(ObjectRetrievalFailureException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody Map<String, Object> handleValidationException(ObjectRetrievalFailureException ex) throws IOException {
        Map<String, Object>  map = new HashMap();
        map.put("error", "Entity Not Found");
        map.put("cause", ex.getMessage());
        return map;
    }*/



    private Map<String, Map<String, Object>> convertConstraintViolation(Set<ConstraintViolation<?>> constraintViolations) {
        Map<String, Map<String, Object>> result = new HashMap();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            Map<String, Object> violationMap = new HashMap();
            violationMap.put("value", constraintViolation.getInvalidValue());
            violationMap.put("type", constraintViolation.getRootBeanClass());
            violationMap.put("message", constraintViolation.getMessage());
            result.put(constraintViolation.getPropertyPath().toString(), violationMap);
        }
        return result;
    }

    private Map<String, List<ViolationVO>> convertConstraintViolation(Errors ex) {
        Map<String, List<ViolationVO>> result = new HashMap();
        String lastObjectName = null;
        List<ViolationVO> violationList = null;
        for (ObjectError error : ex.getAllErrors()) {
            if(lastObjectName != error.getObjectName()){
                lastObjectName = error.getObjectName();
                violationList = new ArrayList<ViolationVO>();
                result.put(error.getObjectName(), violationList);
            }
            violationList.add(
                new ViolationVO(
                    ((FieldError) error).getField(),
                    ((FieldError) error).getRejectedValue() + "",
                    messageSource.getMessage(error, Locale.getDefault())
                )
            );

        }
        return result;
    }

    private String resolveMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}
