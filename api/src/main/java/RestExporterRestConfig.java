import entity.repository.validation.CidadeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * Created by Y31V on 11/06/2014.
 */
@Configuration
public class RestExporterRestConfig extends RepositoryRestMvcConfiguration {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    Validator cidadeValidator() {
        return new CidadeValidator();
    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
       //JSR303 para todas nas entidades
        v.addValidator("beforeSave", validator());
        v.addValidator("beforeCreate", validator());

        //validações de negócio
        v.addValidator("beforeCreate", cidadeValidator());
    }

}