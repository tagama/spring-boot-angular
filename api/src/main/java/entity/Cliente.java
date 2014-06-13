package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Y31V on 29/05/2014.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document


public class Cliente extends AbstractDocument {

    @NotEmpty
    private String email;
    @NotNull
    private Date dataInicioRelacionamento;
    private Endereco endereco;
    private List<String> telefones;
    private PessoaJuridica pessoaJuridica;
    private PessoaFisica pessoaFisica;

}
