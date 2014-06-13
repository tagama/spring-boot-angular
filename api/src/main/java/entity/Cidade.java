package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Y31V on 03/06/2014.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Validated
public class Cidade extends AbstractDocument{

    @NotBlank
    private String nome;
    @NotBlank
    private String estado;
}
