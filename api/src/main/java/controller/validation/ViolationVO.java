package controller.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Y31V on 04/06/2014.
 */
@Getter
@AllArgsConstructor
public class ViolationVO {

    private String field, rejectedValue, message;
}
