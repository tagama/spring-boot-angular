package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by Y31V on 03/06/2014.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridica {

    @NotBlank
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Date fundacao;
    private String website;

}
