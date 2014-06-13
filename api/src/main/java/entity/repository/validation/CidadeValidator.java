package entity.repository.validation;

import entity.Cidade;
import entity.repository.CidadeRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

/**
 * Created by Y31V on 11/06/2014.
 */
public class CidadeValidator implements Validator {
    @Resource
    private CidadeRepository cidadeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Cidade.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cidade o = (Cidade) target;
        if (cidadeRepository.countByNomeAndEstadoAllIgnoreCase(o.getNome(), o.getEstado()) > 0) {

            errors.rejectValue("nome,estado","NotUniqueObject");
        }
    }
}
